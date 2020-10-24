package ohtu.ohtuvarasto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
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
    public void lisääminenEiTäytäYliKapasiteetin() {
        varasto.lisaaVarastoon(12);

        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenAlkusaldoKorjataan() {
        Varasto varastoUusi = new Varasto(-1);
        assertEquals(0, varastoUusi.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisäsyEiMuutaSaldoa() {
        varasto.lisaaVarastoon(5);
        
        varasto.lisaaVarastoon(-5);
        
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttoEiMuutaSaldoa() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(-5);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void yliSaldonOttoTyhjentääVaraston() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(6);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringinMuotoiluOikea() {
        varasto.lisaaVarastoon(4);
        
        assertEquals("saldo = 4.0, vielä tilaa 6.0", varasto.toString());
    }
    
    @Test
    public void alkusaldollisellaVarastollaOikeaTilavuus() {
        Varasto alkusaldoVarasto = new Varasto(10, 5);
        
        assertEquals(10, alkusaldoVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void alkusaldollisellaVaratsollaOikeaAlkusaldo() {
        Varasto alkusaldoVarasto = new Varasto(10, 5);
        
        assertEquals(5, alkusaldoVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenTilavuusEiMahdollinenAlkusaldollisellaVarsatolla() {
        Varasto alkusaldoVarsato = new Varasto(-5, 5);
        
        assertEquals(0, alkusaldoVarsato.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenAlkusaldoEiMahdollinen() {
        Varasto alkusaldoVarsato = new Varasto(10, -5);
        
        assertEquals(0, alkusaldoVarsato.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void alkusaldoEiVoiOllaTilavuuttaSuurempi() {
        Varasto alkusaldoVarasto = new Varasto(10, 15);
        
        assertEquals(10, alkusaldoVarasto.getSaldo(), vertailuTarkkuus);
    }

}
