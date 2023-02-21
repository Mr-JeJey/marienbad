class TestBinaire {
    void principal () {
        char premierJoueur;
        do {
            premierJoueur = SimpleInput.getChar("Qui joue en premier ? (a = Ordinateur | b = Joueur) : ");
        } while ((premierJoueur != 'a') && (premierJoueur != 'b'));
    }
}

