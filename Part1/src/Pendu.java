import java.util.Arrays;

public class Pendu {
    void principal () {
        //Dictionnaire des mots du jeu
        String[] dico;
        dico = creerDico();
        //partie(dico);
        
        tests();
    }
        
    
    void tests () {
        
        testCreerDico();
        testChoisirMot();
        testCreerReponse();
        testAfficheReponse();
        testTester();
        testEstComplet();
        testPartie ();
    }
    
    /**
     * Création d'un dictionnaire de mots
     * @return dictionnaire initialisé
     */
    String[] creerDico () {
        String[] dico = new String[4];
        dico[0] = "MAGNIFIQUE";
        dico[1] = "ORDINAIRES";
        dico[2] = "INCOMPLETE";
        dico[3] = "ABOMINABLE";
        return dico;
    }
    
    /**
     * Test de la méthode creerDico()
     */
    void testCreerDico() {
        String[] dico;
        
        System.out.println ();
        System.out.println ("*** testCreerDico()");
        
        // Act
        dico = creerDico();
        
        // Assert
        System.out.println (Arrays.toString(dico));
        System.out.println();
    }
    
    /**
     * choix aléatoire d'un mot dans un dictionnaire
     * @param dico dictionnaire des mots à choisir
     * @return chaine choisie de manière aléatoire
     */
     String choisirMot (String[] dico) {
         int i;
         //dico = creerDico();
         i = (int) (Math.random () * (dico.length));
         return dico[i];
     }
     
    /**
     * Test de choisirMot()
     */
    void testChoisirMot() {
        String[] dico = {"CHIEN", "CHAT", "SOURIS"};
        
        System.out.println ();
        System.out.println ("*** testChoisirMot()");
        
        testCasChoisirMot (dico);
        
        testCasChoisirMot (dico);
        
        testCasChoisirMot (dico);
        
        testCasChoisirMot (dico);
    }
    
    /**
     * teste un appel à choisirMot()
     * @param dico dictionnaire des mots à choisir
     */
    void testCasChoisirMot(String[] dico){
        // Arrange
        System.out.print ("choisirMot (" + Arrays.toString(dico) + ") : ");
        
        // Act
        String motChoisi = choisirMot (dico);
        
        // Assert
        System.out.println (motChoisi);
    }
    
    /**
     * création d'un tableau de reponse contenant des '_'
     * @param lg longueur du tableau à créer
     * @return tableau de reponse contenant des '_'
     */
    char[] creerReponse(int lg) {
        char[] reponse = new char[lg];
        for (int i = 0; i < reponse.length; i++) {
            reponse[i] = '_';
        }
        return reponse;
    }
         
    /**
     * Test de creerReponse()
     */
    void testCreerReponse() {
        System.out.println ();
        System.out.println ("*** testCreerReponse()");
        
        testCasCreerReponse (5);
        
        testCasCreerReponse (0);
    }
    
    /**
     * teste un appel de creerReponse()
     * @param n taille de la réponse à créer
     */
    void testCasCreerReponse (int n){
        System.out.print ("creerReponse ("+ n +") : ");
        char[] reponse1 = creerReponse (n);
        for (int i = 0 ; i < reponse1.length; i++) {
            System.out.print (reponse1[i] + " ");
        }
        System.out.println ();
    }
    
    /**
     * affiche la réponse du joueur
     * @param reponse reponse du joueur
     */
    void afficherReponse(char[] reponse) {
        for (int i = 0 ; i < reponse.length; i++) {
            System.out.print (reponse[i] + " ");
        }
        System.out.println ();
    }
    
    /**
     * Test de afficheReponse()
     */
    void testAfficheReponse () {
   
        System.out.println ();
        System.out.println ("*** testAfficheReponse()");
        
        char[] reponse1 = {'P', 'R', 'O', 'G', 'R', 'A', 'M', 'M', 'E'};
        testCasAfficheReponse (reponse1);
        
        char[] reponse2 = {};
        testCasAfficheReponse (reponse2);
    }
    
    /**
     * teste un appel à afficherReponse()
     * @param reponse tableau des réponse à afficher
     */
    void testCasAfficheReponse (char[] reponse) {
    
        System.out.print ("afficherReponse (" + Arrays.toString(reponse) + ") : ");
        afficherReponse (reponse);
    }
    
    /**
     * teste la présence d'un caractère dans le mot
     * et le place au bon endroit dans réponse
     * @param mot     mot à deviner
     * @param reponse réponse à complter si le caractère est présent dans le mot
     * @param car     caractère à chercher dans le mot
     * @return vrai ssi le caractère est dans le mot à deviner
     */
    boolean tester (String mot, char[] reponse, char car) {
        boolean trouve = false;
        for (int i = 0; i < mot.length(); i++) {
            if (car == mot.charAt(i)) {
                reponse[i] = car;
                trouve     = true;
            }
        }
        return trouve;
    }
    
    /**
     * Test de tester()
     */
    void testTester() {
        System.out.println();
        System.out.println ("*** testTester()");
        
        char[] reponse1 = {'_' ,'_' ,'_' ,'_' ,'_' ,'_' };
        testCasTester ("RARETE", reponse1, 'T', true);
        
        char[] reponse2 = {'_' ,'_' ,'_' ,'_' ,'_' ,'_' };
        testCasTester ("RARETE", reponse2, 'Y', false);
        
        char[] reponse3 = {'_' ,'_' ,'_' ,'_' ,'_' ,'_' };
        testCasTester ("RARETE", reponse3, 'R', true);
        
        String mot4     = "RARETE";
        char[] reponse4 = {'R' ,'_' ,'R' ,'_' ,'_' ,'_' };
        testCasTester ("RARETE", reponse4, 'R', true);
    }
    
    /**
     * teste un appel de tester()
     * @param mot     mot à deviner
     * @param reponse réponse à complter si le caractère est présent dans le mot
     * @param car     caractère à chercher dans le mot
     * @param result  resultat attendu
     */
    void testCasTester (String mot, char[] reponse, char car, boolean result) {
        // Arrange
        System.out.print ("test (\"" + mot + "\"," 
            + Arrays.toString(reponse) + "' " + car +"') = " + result + " : ");
            
        // Act
        boolean resExec = tester (mot, reponse, car);
        
        //Assert
        if (resExec == result) {
            System.out.println ("OK");
        } else {
            System.err.println ("ERREUR");
        }
        System.out.println (Arrays.toString(reponse));
        System.out.println();
    }
    
    /**
     * rend vrai ssi le mot est trouvé
     * @param mot     mot à deviner
     * @param reponse réponse du joueur
     * @return vrai ssi le mot est égal caractère par caractère à la réponse
     */
    boolean estComplet (String mot, char[] reponse) {
        boolean id = true;
        int i  = 0;
        while (id && i < reponse.length) {
            if (mot.charAt(i) != reponse[i]) {
                id = false;
            }
            i = i + 1;
        }
        return id;
    }
    
    /**
     * Test de estComplet()
     */
    void testEstComplet() {
        System.out.println ();
        System.out.println ("*** testComplet()");
        
        char[] reponse1 = {'_' ,'_' ,'_' ,'_' ,'_' ,'_' };
        testCasEstComplet ("RARETE", reponse1, false);
        
        char[] reponse2 = {'R' ,'_' ,'R' ,'_' ,'_' ,'_' };
        testCasEstComplet ("RARETE", reponse2, false);
        
        char[] reponse3 = {'R' ,'A' ,'R' ,'E' ,'_' ,'E' };
        testCasEstComplet ("RARETE", reponse3, false);
        
        char[] reponse4 = {'R' ,'A' ,'R' ,'E' ,'T' ,'E' };
        testCasEstComplet ("RARETE", reponse4, true);
    }
    
    /**
     * rend vrai ssi le mot est trouvé
     * @param mot     mot à deviner
     * @param reponse réponse du joueur
     * @param result  résultat attendu
     */
    void testCasEstComplet (String mot, char[] reponse, boolean result){
        //Arrange
         System.out.print ("estComplet (\"" + mot + "\", "
            + Arrays.toString(reponse) + ") = "+ result + " : ");
            
        //Act
        boolean resExec = estComplet (mot, reponse);
        
        // Assert
        if (resExec == result) {
            System.out.println ("OK");
        } else {
            System.err.println ("ERREUR");
        }
    }
    
    /**
     * lancement d'une partie du jeu du pendu
     * @param dico dictionnaire des mots à deviner
     */
    void partie(String[] dico) {
        int nbEssais;   // nb d'essais infructueux
        char[] reponse; // reponse du joueur
        String mot;     // mot à deviner
        char car;       // caractère à tester
        boolean trouve; 
        
        // choix d'un mot à deviner
        mot = choisirMot(dico);
    
        // initialisation de la réponse de l'unilisateur
        reponse = creerReponse(mot.length());
    
        // lancement de la partie avec 9 essais maximum
        afficherReponse(reponse);
        nbEssais = 9;
        do {
            System.out.println ("Il reste " + nbEssais + " essais.");
            car = SimpleInput.getChar ("Proposition");
            trouve = tester(mot, reponse, car);
            afficherReponse(reponse);
            if (!trouve) {
                nbEssais = nbEssais - 1;
            } 
        } while (nbEssais != 0 && !estComplet(mot, reponse));
            
        if (estComplet(mot, reponse)) {
            System.out.println ("BRAVO VOUS AVEZ GAGNÉ !");
        } else {
            System.out.println ("Le mot est : " + mot);
        }
    }      
    
    /**
     * test de partie()
     */
    void testPartie () {
        System.out.println ();
        System.out.println ("*** testPartie()");
        
        String[] dico1 = {"RARETE"};
        testCasPartie (dico1, "*** Saisir R A E Y T pour gagner la partie");
        
        String[] dico2 = {"******"};
        testCasPartie (dico2, "*** Saisir des lettres pour perdre la partie");
    }
    
    /**
     * teste un appel de partie()
     * @param dico dictionnaire des mots à deviner
     * @param message instruction au testeur
     */
    void testCasPartie (String[] dico, String message){
        // Arrange
        System.out.println ("Partie (" + Arrays.toString(dico) + ")");
        System.out.println (message);
        
        // Act
        partie(dico);
        System.out.println ();
    }
        
}
