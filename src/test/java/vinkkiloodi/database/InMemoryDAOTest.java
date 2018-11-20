package vinkkiloodi.database;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import vinkkiloodi.domain.Kirjavinkki;
import vinkkiloodi.domain.Vinkki;

public class InMemoryDAOTest {
    private InMemoryDAO dao;
    
    @Before
    public void setUp() {
        dao = new InMemoryDAO();
    }
    
    @Test 
    public void lisaysLisaaElementinTietokantaan() {
        int alkuKoko = dao.getAll().size();
        
        Kirjavinkki vinkki = new Kirjavinkki("Testi", "Testi");
        
        dao.add(vinkki);
        
        assert(dao.getAll().size() > alkuKoko);
    }
    
    @Test 
    public void elementtiLoytyyListaltaLisayksenJalkeen() {
        int alkuKoko = dao.getAll().size();
        
        Kirjavinkki vinkki = new Kirjavinkki("Testi2", "Testi2");
        
        dao.add(vinkki);
        
        boolean loytyi = false;
        
        for (Vinkki v : dao.getAll()) {
            if (v.getOtsikko().equals(vinkki.getOtsikko())) {
                loytyi = true;
            }
        }
        
        assert(loytyi);
    }
    
    @Test 
    public void idHakuLoytaaOikeanElementin() {
        int alkuKoko = dao.getAll().size();
        
        Kirjavinkki vinkki = new Kirjavinkki("Testi3", "Testi3");
        Kirjavinkki vinkki2 = new Kirjavinkki("Testi4", "Testi4");
        
        dao.add(vinkki);
        dao.add(vinkki2);
        
        Vinkki tulos = dao.getById(vinkki.getId());
        
        assertEquals(tulos, vinkki);
    }
    
    @Test 
    public void olematonIdPalauttaaNull() {
        int alkuKoko = dao.getAll().size();
        
        Kirjavinkki vinkki = new Kirjavinkki("Testi5", "Testi5");
        
        dao.add(vinkki);
        
        Vinkki tulos = dao.getById(Integer.MAX_VALUE);
        
        assertEquals(tulos, null);
    }
    
    @Test 
    public void paivitysMuuttaaKirjoittajaa() {
        Kirjavinkki vinkki = new Kirjavinkki("Alkuperäinen", "Alkuperäinen");
        
        dao.add(vinkki);
        
        Kirjavinkki uusiVinkki = new Kirjavinkki("Uusi Kirjoittaja", "Alkuperäinen");
        
        dao.update(vinkki.getId(), uusiVinkki);
        
        Vinkki tulos = dao.getById(vinkki.getId());
        
        assertEquals(tulos.getKirjoittaja(), "Uusi Kirjoittaja");
    }
    
    @Test 
    public void päivitysMuuttaaOtsikkoa() {
        Kirjavinkki vinkki = new Kirjavinkki("Alkuperäinen", "Alkuperäinen");
        
        dao.add(vinkki);
        
        Kirjavinkki uusiVinkki = new Kirjavinkki("Alkuperäinen", "Uusi Otsikko");
        
        dao.update(vinkki.getId(), uusiVinkki);
        
        Vinkki tulos = dao.getById(vinkki.getId());
        
        assertEquals(tulos.getOtsikko(), "Uusi Otsikko");
    }
    
    @Test 
    public void paivitysMuuttaaLuettua() {
        Kirjavinkki vinkki = new Kirjavinkki("Alkuperäinen", "Alkuperäinen");
        
        dao.add(vinkki);
        
        Kirjavinkki uusiVinkki = new Kirjavinkki("Alkuperäinen", "Alkuperäinen", 1, "");
        
        dao.update(vinkki.getId(), uusiVinkki);
        
        Vinkki tulos = dao.getById(vinkki.getId());
        
        assertEquals(tulos.getLuettu(), 1);
    }
}
