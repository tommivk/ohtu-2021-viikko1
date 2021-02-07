package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto uusiVarasto;
    Varasto kelvotonVarasto;
    Varasto kelvotonVarastoSaldolla;
    Varasto kelvotonVarastoNegSaldolla;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        uusiVarasto = new Varasto(20,10);
        kelvotonVarasto = new Varasto(-4);
        kelvotonVarastoSaldolla = new Varasto(-6, 3);
        kelvotonVarastoNegSaldolla = new Varasto(-6, -3);

    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test 
    public void saldoEiMeneYli() {
        varasto.lisaaVarastoon(20);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivisenLisaysEiMuutaSaldoa() {
        varasto.lisaaVarastoon(-4);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test 
    public void negatiivisenMaaranOttaminenEiMuutaSaldoa() {
        varasto.otaVarastosta(-5);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void saldoOnNollaKunOtetaanLiikaa() {
        uusiVarasto.otaVarastosta(100);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void tilavuusOnOikea() {
        assertEquals(20, uusiVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void saldoOnOikea() {
        assertEquals(10, uusiVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void toStringPalauttaaOikeanMerkkijonon() {
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }

    @Test 
    public void kelvottomallaVarastollaOikeaTilavuus() {
        assertEquals(0, kelvotonVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, kelvotonVarastoSaldolla.getTilavuus(), vertailuTarkkuus);
    }

    @Test 
    public void kelvottomallaVarastollaOikeaSaldo() {
        assertEquals(0, kelvotonVarastoNegSaldolla.getTilavuus(), vertailuTarkkuus);
    }


}