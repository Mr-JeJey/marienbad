class Marienbad {
    
    public static void main(String[] args) {
        Marienbad m = new Marienbad();
        m.tests();
        m.jeu();
    }
    
    void tests () {
        testCreerTableau();
        testPartieEnCours();
        testEnlever();
        testOuExclusif();
        testAffichage();
        testAffichageFinPartie();
        testOptimisationVictoire();
    }
    
    /**
     * Cette méthode utilise toutes les autres méthodes permettant de créer et 
     * de jouer à Marienbad
     */
    void jeu() {
        String joueur1 = SimpleInput.getString("Nom du joueur : ");
        String joueur2 = "Ordinateur";
        char premierJoueur;
        String joueur = ""; // Savoir qui joue
        int nombreLignes;
        int nombreTours = 0;
        int[] tableau;
        
        do {
            nombreLignes = SimpleInput.getInt("Avec quel nombre de lignes souhaitez-vous jouer ? (3 minimum) ");
        } while (nombreLignes <= 2);
        
        
        tableau = creerTableau(nombreLignes); 
        
        
        do {
            premierJoueur = SimpleInput.getChar("Qui joue en premier ? (a = Joueur | b = Ordinateur) : ");
        } while ((premierJoueur != 'a') && (premierJoueur != 'b'));
        
        if (premierJoueur == 'b') {
            joueur2 = joueur1;
            joueur1 = "Ordinateur";
        }
        
        
        while (partieEnCours(tableau)) {
            affichage(tableau);
            if (nombreTours % 2 == 0) { 
                joueur = joueur1;
            } else {
                joueur = joueur2;
            }
            
            
            enlever(tableau, joueur);
            nombreTours = nombreTours + 1;
        }
        
        affichageFinPartie(joueur);
        
    }
    

    
    /**
     * Vérifie si la partie en cours est terminée ou non.
     * @param tab  le tableau représentant le jeu
     * @return : true si partie en cours, false si elle est terminée
     */
    boolean partieEnCours(int[] tab) {
        boolean res = false;
        int i = 0;
        while ((i < tab.length) && !res) {
            if (tab[i] != 0) {
                res = true;
            }
            i = i + 1;
        }
        return res;
    }
    
    /**
     * Test de partieEnCours()
     */
    void testPartieEnCours () {
        System.out.println ();
        System.out.println ("*** testPartieEnCours()");
        testCasPartieEnCours (new int[] {1, 3, 5, 7, 9}, true);
        testCasPartieEnCours (new int[] {1, 3, 5, 7}, true);
        testCasPartieEnCours (new int[] {1, 3, 5, 7, 9, 11, 13, 15, 17}, true);
        testCasPartieEnCours (new int[] {0, 0, 0, 0, 0, 0, 0, 0}, false);
        testCasPartieEnCours (new int[] {0}, false);
    }
    
    /**
     * teste un appel à partieEnCours()
     * @param tab tableau contenant des entiers représentant le jeu
     * @param result résultat attendu
     */
    void testCasPartieEnCours (int[] tab, boolean result) {
        System.out.print("partieEnCours(");
        afficherTab(tab);
        System.out.print(")\t = " + result + "\t : ");
        // Act
        boolean resExec = partieEnCours(tab);
        // Assert
        
        
        if (resExec == result){
            System.out.println ("OK");
        } else {
            System.err.println ("ERREUR");
        }
    }
    
    
    /**
     * Enlever une ou plusieurs allumettes du jeu
     * @param tab  le tableau représentant le jeu
     * @param joueur  le nom du joueur qui doit jouer
     */
    void enlever(int[] tab, String joueur) {
        int ligne = -1;
        int nbreAlum = -1;
        System.out.println("C'est au tour de : " + joueur);
        
        if (joueur != "Ordinateur") {
            do {
                ligne = SimpleInput.getInt("Sur quelle ligne voulez-vous jouer ? ");
            } while ((ligne > tab.length - 1) || (ligne < 0) || tab[ligne] == 0);
            
            
            do {
                nbreAlum = SimpleInput.getInt("Combien d'allumettes souhaitez-vous retirer ? ");
            } while ((nbreAlum > tab[ligne]) || (nbreAlum <= 0));
        } else {
            int[] aEnlever = optimisationVictoire(tab);
            ligne = aEnlever[0];
            nbreAlum = aEnlever[1];
            System.out.println("Je joue sur la ligne : " + ligne);
            System.out.println("J'enlève : " + nbreAlum + " allumettes.");
        }
        
        tab[ligne] = tab[ligne] - nbreAlum;
    }
    
    /**
     * Test de enlever()
     */
    void testEnlever () {
        System.out.println ();
        System.out.println ("*** testEnlever()");
        testCasEnlever (new int[] {1, 3, 5, 7}, "testEnlever"); 
        testCasEnlever (new int[] {1, 0, 1, 4}, "Ordinateur");
        testCasEnlever (new int[] {1, 3, 1, 4}, "Ordinateur");
    }
    
    /**
     * teste un appel à enlever()
     * @param tabDepart tableau contenant des entiers représentant un jeu
     * @param joueur joueur qui joue 
     */
    void testCasEnlever (int[] tabDepart, String joueur) {
        
        System.out.print("enlever(");
        afficherTab(tabDepart);
        System.out.print(") : ");
        
        // Act
        System.out.println();
        affichage(tabDepart);
        enlever(tabDepart, joueur); // Verification visuelle
        affichage(tabDepart);
        System.out.println();
    }
    
    /**
     * Regarder si le bot est dans une position favorable
     * 
     * Cette méthode prend des un tableau d'entiers en paramètre, les transformes en
     * binaire et effectue un XOR des valeurs colonne par colonne. 
     * Si deux bits valent 1, alors elle renvoie 0 (pair)
     * Si on a un bit qui vaut 0 et un autre qui vaut 1, elle renvoie 1 (impair)
     * 
     * @param tab  le tableau représentant le jeu
     * @return int : si int = 0, tous les éléments sont pairs
     *               sinon, tous les éléments ne sont pas pairs
     */
    int ouExclusif(int[] tab) {
        int ret = 0;
        
        for (int i = 0; i < tab.length; i++) {
            ret = ret ^ tab[i]; 
        }
        
        return ret;
    }
    
    /**
     * Test de ouExclusif()
     */
    void testOuExclusif () {
        System.out.println ();
        System.out.println ("*** testOuExclusif()");
        testCasOuExclusif (new int[] {1, 3, 5, 7}, 0); 
        testCasOuExclusif (new int[] {1, 0, 1, 4}, 4);
        testCasOuExclusif (new int[] {1, 0, 1, 7}, 7);
    }
    
    /**
     * teste un appel à ouExclusif()
     * @param tab tableau contenant des entiers représentant un jeu
     * @param result résultat attendu après l'exécution
     */
    void testCasOuExclusif (int[] tab, int result) {
        System.out.print("ouExclusif(");
        afficherTab(tab);
        System.out.print(")\t = " + result + "\t : ");
        // Act
        int resExec = ouExclusif(tab);
        
        if (resExec == result) {
            System.out.println("OK");
        } else {
            System.out.println("ERREUR");
        }
    }
    
    /**
     * Méthode permettant d'optimiser les chances de victoires du bot
     * 
     * Différents tests sont effectués afin de trouver une position 
     * optimale pour que le bot puisse gagner.
     * On travaille sur une copie du tableau passé en paramètre afin
     * de faire le plus de tests sans avoir un risque de corrompre 
     * le jeu.
     * 
     * @param tab  le tableau représentant le jeu
     * @return int[] contenant la ligne à l'indice 0 et le nombre d'allumettes à l'indice 1
     */
    int[] optimisationVictoire(int[] tab) {
        int[] ret = new int[2];
        int[] copie = new int[tab.length];
        boolean parcourir = true;
        boolean finit = false;
        int memorisation = 0;
        int ligne = 0;
        int nbreAlum = 0;
        

        for (int k = 0; k < tab.length; k++) {
            copie[k] = tab[k];
        }
        
        if (ouExclusif(copie) == 0) { // Position perdante pour le bot
            for (int l = 0; (l < tab.length) && parcourir; l++) {
                if (tab[l] != 0) {
                    parcourir = false;
                    ret = new int[] {l, 1}; // Enlève une alumette où l'on peut : On attend une erreur du joueur
                }
            }
        } else {
            while (ouExclusif(copie) != 0 && !(finit)) {
                parcourir = true;
                while (ligne < tab.length && parcourir) {
                    if (nbreAlum == 0) {
                        if (tab[ligne] == 0) {
                            ligne = ligne + 1;
                        } else {
                            parcourir = false;
                        }
                    } else {
                        ligne = ligne + 1;
                        nbreAlum = 0;
                    }
                    
                }
                
                memorisation = copie[ligne]; // Travail sur une copie afin d'effectuer des tests
                while (ouExclusif(copie) != 0 && (copie[ligne] > 0)) {
                    copie[ligne] = copie[ligne] - 1;
                    nbreAlum = nbreAlum + 1;
                    if (ouExclusif(copie) == 0) { // Coup optimal pour se placer en position gagnante
                        finit = true;
                    }
                }
                copie[ligne] = memorisation;
                
                ret = new int[] {ligne, nbreAlum};
            }
            
        }
        
        return ret;
    }
    
    /**
     * Test de optimisationVictoire()
     */
    void testOptimisationVictoire () {
        System.out.println ();
        System.out.println ("*** testOptimisationVictoire()");
        testCasOptimisationVictoire (new int[] {1, 3, 5, 7}, new int[] {0,1}); 
        testCasOptimisationVictoire (new int[] {1, 3, 2, 7}, new int[] {3,7});
        testCasOptimisationVictoire (new int[] {0, 3, 5, 7}, new int[] {1,1});
        testCasOptimisationVictoire (new int[] {1, 3, 5, 0}, new int[] {2,3});
    }
    
    /**
     * teste un appel à optimisationVictoire()
     * @param tab tableau contenant des entiers représentant un jeu
     * @param result résultat attendu après l'exécution
     */
    void testCasOptimisationVictoire (int[] tab, int[] result) {
        System.out.print("optimisationVictoire(");
        afficherTab(tab);
        System.out.print(")\t = ");
        afficherTab(result);
        System.out.print("\t : ");
        // Act
        int[] resExec = optimisationVictoire(tab);
        boolean verif = true;
        int i = 0;
        
        while ((i < result.length) && verif) {
            if (result[i] != resExec[i]) {
                verif = false;
            }
            i++;
        }
        
        
        if (verif) {
            System.out.println("OK");
        } else {
            System.out.println("ERREUR");
        }
    }
    
    
    /**
     * Création d'un tableau d'entiers
     * @param nbreLigne est le nombre de lignes avec lequel l'utilisateur souhaite jouer
     * @return tableau initialisé
     */
    int[] creerTableau (int nbreLigne) {
        int[] tab;
        int i = 0;
        
        tab = new int[nbreLigne];
        while (i < nbreLigne) {
            tab[i] = 2 * i + 1;
            i = i + 1;
        }
    
        return tab;
    }
    
    /**
     * Test de creerTableau()
     */
    void testCreerTableau () {
        System.out.println ();
        System.out.println ("*** testCreerTableau()");
        testCasCreerTableau (5, new int[] {1, 3, 5, 7, 9});
        testCasCreerTableau (4, new int[] {1, 3, 5, 7});
        testCasCreerTableau (9, new int[] {1, 3, 5, 7, 9, 11, 13, 15, 17});
    }
    
    /**
     * teste un appel à creerTableau()
     * @param tab nombre de lignes que doit contenir le tableau
     * @param result résultat attendu après l'exécution
     */
    void testCasCreerTableau (int tab, int[] result) {        
        System.out.print("creerTableau(" + tab + ") = ");
        afficherTab(result);
        System.out.print(")\t : ");
        
        
        // Act
        int[] resExec = creerTableau(tab);
        // Assert
        int i = 0;
        int j = 0;
        boolean verification = true;
        
        while ((i < resExec.length) && verification) {
            while ((j < result.length) && verification) {
                if (resExec[i] != result[j]) {
                    verification = false;
                }
                i = i + 1;
                j = j + 1;
            }
        }
        
        if (verification){
            System.out.println ("OK");
        } else {
            System.err.println ("ERREUR");
        }
    }
    
    
    /**
     * Affichage des bâtons à partir d'un tableau de valeurs
     * @param tab tableau représentant le jeu
     */
    void affichage (int[] tab) {
        int i = 0;
        int j = 0;
        while (i < tab.length) {
            j = 0;
            System.out.print(i + " : ");
            while (j < tab[i]) {
                System.out.print(" | ");
                j = j + 1;
            }
            System.out.println();
            i = i + 1;
        }
    }
    
    /**
     * Test de affichage()
     */
    void testAffichage () {
        System.out.println ();
        System.out.println ("*** testAffichage()");
        testCasAffichage (new int[] {1, 3, 5, 7});
        testCasAffichage (new int[] {1, 0, 1, 4});
        testCasAffichage (new int[] {0, 0, 0, 0});
        testCasAffichage (new int[] {0, 3, 2, 1});
    }
    
    /**
     * teste un appel à affichage()
     * @param tab tableau d'entiers représentant le jeu
     */
    void testCasAffichage(int[] tab) {
        affichage(tab); // Vérification visuelle dans le terminal
        System.out.println();
    }
    
    
    /**
     * Affichage du nom du vainqueur à la fin d'une partie
     * @param tab  nom du gagnant
     */
    void affichageFinPartie(String joueur) {
        System.out.println("Partie terminée ! Le vainqueur est : " + joueur);
    }
    
    /**
     * Test de affichageFinPartie()
     */
    void testAffichageFinPartie() {
        System.out.println ();
        System.out.println ("*** testAffichageFinPartie()");
        testCasAffichageFinPartie ("Jeremy");
        testCasAffichageFinPartie ("");
    }
    
    /**
     * teste un appel à affichageFinPartie()
     * @param nomGagnant le nom du joueur ayant gagné la partie
     */
    void testCasAffichageFinPartie(String nomGagnant) {
        affichageFinPartie(nomGagnant); // Vérification visuelle dans le terminal
        System.out.println();
    }
    
    
    
    /**
     * Méthode permettant d'afficher un tableau (pour les méthodes de tests)
     * @param tab  le tableau a affiché
     */
    void afficherTab(int[] tab) {
        System.out.print("{ ");
        for (int i = 0; i < tab.length; i++) {
            System.out.print(tab[i] + " ");
        }
        System.out.print("}");
    }
    
}

