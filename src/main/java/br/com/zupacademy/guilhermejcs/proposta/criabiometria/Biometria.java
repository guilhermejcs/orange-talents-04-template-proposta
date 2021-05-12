package br.com.zupacademy.guilhermejcs.proposta.criabiometria;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;

@Embeddable
public class Biometria {

    private byte[] digital;

    @Deprecated
    public Biometria() {

    }

    public Biometria(String digital) {
        this.digital = Base64.getEncoder().encode(digital.getBytes());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Biometria biometria = (Biometria) o;
        return Arrays.equals(digital, biometria.digital);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(digital);
    }
}
