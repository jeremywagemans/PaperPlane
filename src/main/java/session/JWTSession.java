package session;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

public class JWTSession implements Session {

    private Map<String, Object> map;
    private String issuer;
    private String secretKey;

    public JWTSession(AppContext cntxt) {
        issuer = cntxt.getDomain();
        secretKey = cntxt.getSecretKey();
    }

    public JWTSession(String jwt, AppContext cntxt) {
        this(cntxt);
        try {
            JWTVerifier verifier = new JWTVerifier(secretKey);
            map = verifier.verify(jwt);
        } catch (JWTVerifyException e) {
            // Invalid Token
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SignatureException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Object getAttribute(String key) {
        return map.get(key);
    }

    public void setAttribute(String key, Object value) {
        if (map == null) {
            map = new HashMap<String, Object>();
        }
        map.put(key, value);
    }

    public String getWebToken() {
        final long iat = System.currentTimeMillis() / 1000L; // issued at claim
        final long exp = iat + (60 * 60 * 24 * 365); // expires claim. In this case the token expires in
        // a year
        final JWTSigner signer = new JWTSigner(secretKey);
        final HashMap<String, Object> claims = new HashMap<String, Object>();
        claims.put("iss", issuer);
        claims.put("exp", exp);
        claims.put("iat", iat);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            claims.put(entry.getKey(), entry.getValue());
        }
        return signer.sign(claims);
    }
}
