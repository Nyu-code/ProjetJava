public class Potion extends Item {
    public static final String categorieP                 = "potion";
    public String type;    //type dans le tableau tabPotion
    public int     degat;
    public int    duree;
    public static final String     [] tabPotion     =     {"poison"    ,"molotov"    ,"soin"    ,"explosion","mana"};
    public static final int     [] dureePot     =    {3            ,2            ,1        ,1            ,1        };
    public static final int     [] dgtPot         =    {1            ,1            ,-1        ,2            ,0        };
    
    
    public Potion(String type) {
        super(categorieP);
        this.type = type;
        int i = getIndicePot(type);
        this.degat = dgtPot[i];
        this.duree = dureePot[i];
    }
    
    public int getIndicePot(String type) {
        int i = 0;    //comme on a bien ordonnée les choses, l'indice correspond à la bonne potion, à sa durée, à ses dégâts.
        try {
            while (type!=tabPotion[i]) {
                i++;
            }
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("La potion n'existe pas.");
        }
        return i;
    }
    
    public int getDureePot(String type) {
        return dureePot[getIndicePot(type)];
    }
    
    public int getDgtPot(String type) {
        return dgtPot[getIndicePot(type)];
    }
    
}