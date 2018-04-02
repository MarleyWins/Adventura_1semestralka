package logika;

import main.Start;

/**
 * Třída Hra - třída představující logiku adventury.
 *
 * Toto je hlavní třída logiky aplikace. Tato třída vytváří instanci třídy
 * HerniPlan, která inicializuje mistnosti hry a vytváří seznam platných příkazů
 * a instance tříd provádějící jednotlivé příkazy. Vypisuje uvítací a ukončovací
 * text hry. Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 * @version pro školní rok 2016/2017
 */
public class Hra implements IHra {

    private final SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private final HerniPlan herniPlan;
    private boolean konecHry = false;
    private final EndingSequence ending;

    /**
     * Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a
     * seznam platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan(this);
        platnePrikazy = new SeznamPrikazu();
        ending = new EndingSequence();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new CommandGo(herniPlan));
        platnePrikazy.vlozPrikaz(new CommandEnd(this));
        platnePrikazy.vlozPrikaz(new CommandTalk(herniPlan));
        platnePrikazy.vlozPrikaz(new CommandHug(herniPlan));
        platnePrikazy.vlozPrikaz(new CommandHit(herniPlan));
        platnePrikazy.vlozPrikaz(new CommandExamine(herniPlan));
        platnePrikazy.vlozPrikaz(new CommandPick(herniPlan));
        platnePrikazy.vlozPrikaz(new CommandDrop(herniPlan));
        platnePrikazy.vlozPrikaz(new CommandInventory(herniPlan));
        platnePrikazy.vlozPrikaz(new CommandGive(herniPlan));

    }

    /**
     * Vrátí úvodní zprávu pro hráče.
     */
    @Override
    public String vratUvitani() {

        herniPlan.getInterface2().printPrompt("My name is Hal 9000, please state your name, so I can be your guide in this world.\n");
        String name;
        name = herniPlan.getInterface2().useInput("Choose your name.");
        if (!name.isEmpty()) {
            herniPlan.getPlayer().setName(name);
        }

        return "You are half robot half human. You are searching for your destiny. And your finel question is Who am I?"
                + herniPlan.getAktualniProstor().dlouhyPopis();
    }

    /**
     * Vrátí závěrečnou zprávu pro hráče.
     */
    @Override
    public String vratEpilog() {

        if (herniPlan.getAktualniProstor().equals(herniPlan.getVictoryRoom())) {
            return ending.endingText(herniPlan.getPlayer(), herniPlan.getInterface2());
        } else {
            return "Thank you for playing. Geronimo";
        }
    }

    /**
     * Vrací true, pokud hra skončila.
     */
    @Override
    public boolean konecHry() {
        return konecHry;
    }

    /**
     * Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo
     * příkazu a další parametry. Pak otestuje zda příkaz je klíčovým slovem
     * např. jdi. Pokud ano spustí samotné provádění příkazu.
     *
     * @param radek text, který zadal uživatel jako příkaz do hry.
     * @return vrací se řetězec, který se má vypsat na obrazovku
     */
    @Override
    public String zpracujPrikaz(String radek) {
        String[] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String[] parametry = new String[slova.length - 1];
        for (int i = 0; i < parametry.length; i++) {
            parametry[i] = slova[i + 1];
        }
        String textKVypsani = " .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.provedPrikaz(parametry);
        } else {
            textKVypsani = "I dont know this command. - Hal 9000";
        }
        return textKVypsani;
    }

    /**
     * Nastaví, že je konec hry, metodu využívá třída PrikazKonec, mohou ji
     * použít i další implementace rozhraní Prikaz.
     *
     * @param konecHry hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        if (Start.getVersion().equals("GUI")) {
            this.konecHry = konecHry;
            Start.lockAll();
        } else {
            this.konecHry = konecHry;
        }
    }

    /**
     * Metoda vrátí odkaz na herní plán, je využita hlavně v testech, kde se
     * jejím prostřednictvím získává aktualní místnost hry.
     *
     * @return odkaz na herní plán
     */
    @Override
    public HerniPlan getHerniPlan() {
        return herniPlan;
    }

    @Override
    public boolean victory() {
        return herniPlan.getVictoryRoom().equals(herniPlan.getAktualniProstor());
    }

}
