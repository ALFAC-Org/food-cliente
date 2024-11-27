package br.com.alfac.foodcliente.unit.infra.helper;

import br.com.alfac.foodcliente.infra.helper.JwtHelper;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class JwtHelperTest {

    private static final String JWT_TOKEN = "Bearer token";
    private static final String TOKEN = "token";
    private static final Long EXPECTED_ID = 123L;
    private static final String EXPECTED_WHO = "user";

    private DecodedJWT decodedJWT;
    private Claim idClaim;
    private Claim whoClaim;

    @BeforeEach
    void setUp() {
        decodedJWT = Mockito.mock(DecodedJWT.class);
        idClaim = mock(Claim.class);
        whoClaim = mock(Claim.class);

        when(idClaim.asLong()).thenReturn(EXPECTED_ID);
        when(whoClaim.asString()).thenReturn(EXPECTED_WHO);
        when(decodedJWT.getClaim("id")).thenReturn(idClaim);
        when(decodedJWT.getClaim("who")).thenReturn(whoClaim);
    }

    @Test
    void testGetID() {
        try (MockedStatic<JWT> jwtMockedStatic = mockStatic(JWT.class)) {
            jwtMockedStatic.when(() -> JWT.decode(TOKEN)).thenReturn(decodedJWT);

            Long id = JwtHelper.getID(JWT_TOKEN);
            assertEquals(EXPECTED_ID, id);
        }
    }

    @Test
    void testGetWho() {
        try (MockedStatic<JWT> jwtMockedStatic = mockStatic(JWT.class)) {
            jwtMockedStatic.when(() -> JWT.decode(TOKEN)).thenReturn(decodedJWT);

            String who = JwtHelper.getWho(JWT_TOKEN);
            assertEquals(EXPECTED_WHO, who);
        }
    }
}
