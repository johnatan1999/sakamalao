package mg.sakamalao.common.core.domain.enums;

public enum ExpenseCategory {
    // 👥 Ressources humaines
    SALARY,                 // Salaires
    BONUS,                  // Primes
    FREELANCE,              // Prestataires / freelances
    TRAINING,               // Formation

    // 🏢 Fonctionnement
    RENT,                   // Loyer
    UTILITIES,              // Eau, électricité, internet
    OFFICE_SUPPLIES,        // Fournitures de bureau
    MAINTENANCE,            // Maintenance / réparations

    // 💻 IT & outils
    SOFTWARE,               // Licences, abonnements SaaS
    HARDWARE,               // Matériel informatique
    CLOUD,                  // Hébergement, cloud, serveurs

    // 🚗 Déplacements
    TRANSPORT,              // Transport (taxi, carburant, billets)
    ACCOMMODATION,          // Hôtel
    MEALS,                  // Repas professionnels

    // 📢 Marketing & ventes
    MARKETING,              // Publicité, marketing
    SALES_COMMISSION,       // Commissions commerciales
    EVENTS,                 // Salons, événements

    // 💰 Financier & légal
    TAX,                    // Taxes
    BANK_FEES,              // Frais bancaires
    LEGAL,                  // Avocats, notaires, conseils juridiques
    INSURANCE,              // Assurances

    // 📦 Autres
    PURCHASE,               // Achats divers
    OTHER
}
