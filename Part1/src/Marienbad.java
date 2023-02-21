class Marienbad {
    void principal () {
        //tests();
        
        jeu();
    }
    
    void tests () {
        testCreerTableau();
        testPartieEnCours();
        testEnlever();
        testAffichage();
        testAffichageFinPartie();
    }
     
    /**
     * Cette méthode utilise toutes les autres méthodes permettant de créer et 
     * de jouer à Marienbad
     */
    void jeu() {
        String joueur1 = SimpleInput.getString("Nom du joueur 1 : ");
        String joueur2 = SimpleInput.getString("Nom du joueur 2 : ");
        String joueur = "";
        int nombreLigne;
        int nombreTours = 0;
        int[] tableau;
        
        do {
            nombreLigne = SimpleInput.getInt("Avec quel nombre de lignes souhaitez-vous jouer ? (3 minimum) : ");
        } while (nombreLigne <= 2);
        
        
        tableau = creerTableau(nombreLigne);  
        
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
        
        affichageFinPartie(joueur); // Affiche le nom du vainqueur.
        
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
        System.out.print(") \t = " + result + "\t : ");
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
        int ligne;
        int nbreAlum;
        System.out.println("C'est au tour de : " + joueur);
        
        do {
            ligne = SimpleInput.getInt("Sur quelle ligne voulez-vous jouer ? ");
        } while ((ligne > tab.length - 1) || (ligne < 0) || tab[ligne] == 0);
        
        
        do {
            nbreAlum = SimpleInput.getInt("Combien d'allumettes souhaitez-vous retirer ? ");
        } while ((nbreAlum > tab[ligne]) || (nbreAlum <= 0));
        
        tab[ligne] = tab[ligne] - nbreAlum;
    }
    
    /**
     * Test de enlever()
     */
    void testEnlever () {
        System.out.println ();
        System.out.println ("*** testEnlever()");
        testCasEnlever (new int[] {1, 3, 5, 7}, "testEnlever"); 
        testCasEnlever (new int[] {1, 0, 1, 4}, "testEnlever");
    }
    
    /**
     * teste un appel à enlever()
     * @param tabDepart tableau contenant des entiers représentant un jeu
     * @param joueur joueur qui joue 
     */
    void testCasEnlever (int[] tabDepart, String joueur) {
        System.out.print("enlever(");
        afficherTab(tabDepart);
        System.out.println(") : ");
        // Act
        System.out.println();
        affichage(tabDepart);
        enlever(tabDepart, joueur); // Verification visuelle
        affichage(tabDepart);
        System.out.println();
    }
    
    /**
     * Création d'un tableau d'entiers
     * @param nbreLignes est le nombre de lignes avec lequel l'utilisateur souhaite jouer
     * @return tableau initialisé
     */
    int[] creerTableau (int nbreLignes) {
        int[] tab;
        int i = 0;
        
        tab = new int[nbreLignes];
        while (i < nbreLignes) {
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
        System.out.print("creerTableau(" + tab +") \t = ");
        afficherTab(result);
        System.out.print("\t : ");
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
