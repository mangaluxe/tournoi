package org.example.tournoi.util;


public class Levenshtein {

    /**
     * Calculer la distance de Levenshtein entre deux chaînes
     */
    public static int calculLevenshteinDistance(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();

        // Créer une matrice pour stocker les coûts des transformations
        int[][] dp = new int[len1 + 1][len2 + 1];

        // Initialiser la première ligne et la première colonne de la matrice
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i; // coût de supprimer tous les caractères de str1
        }
        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j; // coût d'insérer tous les caractères de str2
        }

        // Remplir la matrice avec les coûts des transformations
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                // Si les caractères sont égaux, pas de coût
                int cost = (str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1;

                // Calculer le coût minimal parmi les trois opérations : insertion, suppression, substitution
                dp[i][j] = Math.min(
                        Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),  // suppression ou insertion
                        dp[i - 1][j - 1] + cost  // substitution
                );
            }
        }

        return dp[len1][len2]; // La distance de Levenshtein est le coût final dans le coin inférieur droit de la matrice
    }

}
