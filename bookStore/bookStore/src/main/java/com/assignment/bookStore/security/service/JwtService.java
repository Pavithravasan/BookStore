package com.assignment.bookStore.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

private static final String SECRET_KEY="WmZ/5uTNi1oITHa+DfdujVX/DYLrbNu26+a9Qz6ulczPMh1cCiPCCLnwiORq1Jy2";

  public String extractUserEmail(String token)
  {
      return extractClaim(token,Claims::getSubject);
  }

  public String generateToken( UserDetails userDetails){
      return Jwts.builder().
              setSubject(userDetails.getUsername()).
              setIssuedAt(new Date(System.currentTimeMillis())).
              setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
              .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
  }

  public boolean isTokenValid(String token,UserDetails userDetails){
      final String username=extractUserEmail(token);
      String username1=userDetails.getUsername();
   boolean validity= isTokenExpired(token);
      if (username.equals(userDetails.getUsername()) &&
              !isTokenExpired(token)){
          return true;
      }
      return false;
  }
  private boolean isTokenExpired(String token){
    if(extractExpiration(token).compareTo(new Date(System.currentTimeMillis()))==1)
    {return false;}
      return true;
  }

  private Date extractExpiration(String token){
      Date x= extractClaim(token,Claims::getExpiration);
      return x;
  }

  private <T> T extractClaim(String token, Function<Claims,T> claimResolver){
      final Claims claims=extracAllClaims(token);
      return claimResolver.apply(claims);
  }
  private Claims extracAllClaims(String token){
      return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
  }

  private Key getSignKey(){
      byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
      return Keys.hmacShaKeyFor(keyBytes);
  }

}
