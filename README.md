1. app

Ce dossier contient la configuration principale de l'application, y compris le fichier AndroidManifest.xml et les composants d'application de base comme l'activité principale (MainActivity.kt) et la classe d'application (CleanRmApiApplication.kt).

    MainActivity.kt : L'activité principale qui sert de conteneur pour les différents écrans.
    CleanPmApiApplication.kt : Classe Application pour initialiser les dépendances et autres configurations globales.

2. core

Le dossier core regroupe les composants qui sont utilisés à travers l'application :

    data : Ce sous-dossier gère les appels API, les bases de données et les implémentations des repositories. Il encapsule toutes les opérations relatives aux données de l'application.

    domain : Ce sous-dossier contient les modèles de données dans models ainsi que les interfaces des repository.

    ui : Ce sous-dossier regroupe les composants d'interface utilisateur qui peuvent être réutilisés, comme les boutons personnalisés, le theme ou la navigation.

3. features

Le dossier features contient les différentes fonctionnalités de l'application. Chaque fonctionnalité est encapsulée dans son propre sous-dossier avec une séparation claire entre la vue (screen) et le modèle de vue (viewmodel).

    screen : Cette partie contient les écrans spécifiques à la fonctionnalité. Chaque fonctionnalité a ses propres écrans (par exemple, CharactersScreen.kt pour la liste des characters).

    viewmodel : Les ViewModels gèrent l'état des screen.
4. Gradle Scripts