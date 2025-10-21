package com.medpred.security;
import org.mindrot.jbcrypt.BCrypt;
public class Passwords {
    public static String hash(String raw) { return BCrypt.hashpw(raw, BCrypt.gensalt(12)); }
    public static boolean verify(String raw, String hashed) { return BCrypt.checkpw(raw, hashed); }
}
