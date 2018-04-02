package logika;


/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2016/2017
 */
public class HerniPlan {
    
    private Prostor aktualniProstor;
    private final Player player;
    private final SecondInterface secondInter;
    private final HugHitResolver hhResolver;
    private Prostor victoryRoom;
    
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();
        player = new Player();
        secondInter = new SecondInterface();
        hhResolver = new HugHitResolver();        
    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory  
      
        Prostor apartment = new Prostor("apartment", "One room apartment. You can see pretty messy table with some penciles and sheets of paper (apparently you did some drawings last night). ");
        Item penciles = new Item("penciles", "old", 'h', 5, 3);
        apartment.addItem(penciles);
        Npc eDI = new Npc("EDI", 'r', "Hello my old friend. I seem to be lost ... What am I supposed to do without you.", 3, true);
        eDI.addConversationOpt(1, "Seek some new technology.");
        eDI.addConversationOpt(2, "Come with me.");
        eDI.addConversationOpt(3, "Hmm, don't worry about it.");
        eDI.addConversationOpt(4, "I don't know.");
        eDI.addConversationOpt(5, "Never mind, just don't forget your place!");
        apartment.addNpc(eDI);
        Item sketches = new Item("Sketches", "Some drawings.", 'h', 5, 3);
        apartment.addItem(sketches);
        Item table = new Item("table", "Old plastic table");
        apartment.addItem(table);
        
        Prostor ruinedApartement = new Prostor("RuinedApartement", "Dirty ruined apartment where no one lives. But there is some screwdriver on the floor…");
        Item screwdriver = new Item("Screwdriver", "Weird looking screwdriver", 'r', 8, 3);
        ruinedApartement.addItem(screwdriver);
        
        Prostor square = new Prostor("Square", "Massive open space where is a statue in the center.");
        Item angelStat = new Item("AngelStatue", "Metalic angle statue holding a shiny orb looking technology. Symbolizes peace between robots and humans.");
        square.addItem(angelStat);
        Npc krista = new Npc("Krista", 'h', "There is no peace between us and robots, is no there?", 3, true);
        krista.addConversationOpt(1, "No, probably not.");
        krista.addConversationOpt(2, "I suppose they will win.");
        krista.addConversationOpt(3, "Maybe, maybe not.");
        krista.addConversationOpt(4, "Well man created them, so ...");
        krista.addConversationOpt(5, "Don't worry our hearts are in the right place.");
        square.addNpc(krista);
        
        apartment.setVychod(square);
        apartment.setVychod(ruinedApartement);
        ruinedApartement.setVychod(square);
        ruinedApartement.setVychod(apartment);
        
        Prostor crwdStreet = new Prostor("CrowdedStreet", "Noisy street full of vendors trying to sell their goods.");
        Item ribbon = new Item("Ribbon", "Bright red ribbon.", 'h', 8, 5);
        crwdStreet.addItem(ribbon);
        Item coin = new Item("Coin", "BitCoin", 'r', 8, 5);
        crwdStreet.addItem(coin);
        
        Npc lois = new Npc("Lois", 'h', "Bonjur, would you like to buy something?", 3, false);
        lois.addConversationOpt(1, "That screw looks nice.");
        lois.addConversationOpt(2, "Why do you useless junk.");
        lois.addConversationOpt(3, "No, nothing. Thank you.");
        lois.addConversationOpt(4, "That's nice book you have there.");
        lois.addConversationOpt(5, "Sure, some lotion please.");
        crwdStreet.addNpc(lois);
        
        Npc aki = new Npc("Aki", 'r', "**Metalic voice** Would you like to buy something ?", 3, true);
        aki.addConversationOpt(1, "Yeah, why not.");
        aki.addConversationOpt(2, "New CPU would be nice.");
        aki.addConversationOpt(3, "No nothing thank you.");
        aki.addConversationOpt(4, "Is there anything not that shiny?");
        aki.addConversationOpt(5, "I don't buy from robots.");
        crwdStreet.addNpc(aki);
        
        Prostor subway = new Prostor("Subway", "Old subway station. Was not used for a long time.");
        Item laptop = new Item("Lenovo", "Not working anymore", 'h', 3, 3);
        subway.addItem(laptop);
        Item redOrb = new Item("RedOrb", "Bright red light.", 'r', 3, 3);
        subway.addItem(redOrb);
        Item digiPainting = new Item("DigitalPainting", "Lively picture of a robot dancer.");
        subway.addItem(digiPainting);
        
        square.setVychod(subway);
        square.setVychod(crwdStreet);
        
        Prostor slums = new Prostor("Slums", "Doesn't look good .. There is lot of sick people, and one poor looking man.");
        Item garbage = new Item("GarbagePile", "Piles and piles of garbage");
        slums.addItem(garbage);
        Npc gil = new Npc("Gil", 'h', "*Cough* Hey you *cough* please could you help me?", 4, true);
        gil.addConversationOpt(1, "Ignore him");
        gil.addConversationOpt(2, "You stink.");
        gil.addConversationOpt(3, "Ehmmm");
        gil.addConversationOpt(4, "Sure ...");
        gil.addConversationOpt(5, "What happend to you? Of course I'll help.");
        slums.addNpc(gil);
        
        Prostor emptyStr = new Prostor("EmptyStreet", "There is almost no one on the street but the atmosphere has strange soothing feeling. With beautifull human looking woman.");
        Npc femShep = new Npc("FemShep9000", 'r', "**Seductive voice**\nHey man ... Are you lonely?", 4, true);
        femShep.addConversationOpt(1, "Too old model for me.");
        femShep.addConversationOpt(2, "Do you have massage settings?");
        femShep.addConversationOpt(3, "Allways");
        femShep.addConversationOpt(4, "Interesting ...");
        femShep.addConversationOpt(5, "**Look for defects**");
        emptyStr.addNpc(femShep);
        
        crwdStreet.setVychod(slums);
        crwdStreet.setVychod(emptyStr);
        
        slums.setVychod(crwdStreet);
        emptyStr.setVychod(crwdStreet);
        
        Prostor tunnel = new Prostor("Tunnel", "Dark and wet tunnel. With someone brave and someone plastic.");
        Item moss = new Item("Moss", "Slimy .... but ever present");
        tunnel.addItem(moss);
        Npc amy = new Npc("AmyPond", 'h', "Hey ... you remind me of someone ... someone with a box.", 10, true);
        amy.addConversationOpt(1, "CyberMan?");
        amy.addConversationOpt(2, "I am not a Dalek. ** Exterminte **");
        amy.addConversationOpt(3, "Your husband maybe?");
        amy.addConversationOpt(4, "River Song");
        amy.addConversationOpt(5, "Melody Pond");
        tunnel.addNpc(amy);
        Npc rorry = new Npc("RorryPond", 'r', "Ah, you are not him. I want him to make me human again", 10, false);
        rorry.addConversationOpt(1, "Guard pandorica as you are supposed to.");
        rorry.addConversationOpt(2, "You have gun in your sleeve ... literaly.");
        rorry.addConversationOpt(3, "Well, I get that a lot.");
        rorry.addConversationOpt(4, "If see him.");
        rorry.addConversationOpt(5, "I will make sure Doctor hears from you. **Whispers** I have number to T.A.R.D.I.S.");
        tunnel.addNpc(rorry);
        
        Prostor abandonedTrain = new Prostor("AbandonedTrain", "Broken, scratched train. And it stinks in here.");
        Item goldenWatch = new Item("GoldenWatch", "The clock is stopped at 5:25 AM July 16th 1945.", 'h', 10, 10);
        abandonedTrain.addItem(goldenWatch);
        
        subway.setVychod(tunnel);
        subway.setVychod(abandonedTrain);
        
        tunnel.setVychod(subway);
        abandonedTrain.setVychod(subway);
        
        Prostor workShop = new Prostor("WorkShop", "Silent. There is man searhing for his screwdriver");
        Npc hefaistos = new Npc("Hefaistos", 'r', "Have you seen my screwdriver?", 15, false);
        hefaistos.addConversationOpt(1, "Yes ... maybe somewhere on a street?");
        hefaistos.addConversationOpt(2, "Yes in apartement.");
        hefaistos.addConversationOpt(3, "Sorry, but I dont remember.");
        hefaistos.addConversationOpt(4, "Yes ... maybe somewhere in a tunnel?");
        hefaistos.addConversationOpt(5, "Yes in apartement.");
        workShop.addNpc(hefaistos);
        
        emptyStr.setVychod(workShop);
        
        Prostor lab = new Prostor("Laboratory", "Luthors's laboratory. And you now Lex is always lonely.");
        Npc lex = new Npc("LexLuthor", 'h', "I dont suppose you have any cryptonite", 15, true);
        lex.addConversationOpt(1, "I know something that could find it.");
        lex.addConversationOpt(2, "Sure, my friend Rorry might have some.");
        lex.addConversationOpt(3, "No, I wont help you kill Clark");
        lex.addConversationOpt(4, "I know someone who could help you.");
        lex.addConversationOpt(5, "Sure, my friend Amy might have few rocks.");
        lab.addNpc(lex);
        
        tunnel.setVychod(lab);
        lab.setVychod(workShop);
        workShop.setVychod(lab);
        
        Prostor victory = new Prostor ("Crossroads","You have reached the end of your brief jurney. You have learned so much");        
        workShop.setVychod(victory);
        lab.setVychod(victory);
        aktualniProstor = apartment;  // hra začíná v domečku
        victoryRoom = victory;
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }

    public Player getPlayer(){
        return this.player;
    }    
    
    public SecondInterface getInterface2(){
        return secondInter;
    }
    
    public HugHitResolver getHH(){
        return hhResolver;
    }
    
    public Prostor getVictoryRoom(){
        return victoryRoom;
    }
    
    public void setVictoryRoom(Prostor prostor){
        this.victoryRoom = prostor;
    }
}
