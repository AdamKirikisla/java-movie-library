/**
 * Program:  Movie.java
 * Author:   Adam Kirikisla
 * Date:     April 2026
 * 
 * Purpose:
 * This program implements a simple Movie Library system using a menu-driven
 * interface. Users can view, add, remove, and clear movies from a collection,
 * as well as export the movie list to a text file.
 *
 * 
 * Features:
 * - View all movies in the library
 * - Add new movies
 * - Remove a movie by index
 * - Remove all movies
 * - Export movie list to a .txt file
 * 
 *
 * Java Skills Used:
 * - Classes and Objects
 * - Constructors
 * - ArrayList 
 * - Array of objects
 * - Loops (for, do-while)
 * - Conditional statements (if, switch)
 * - Exception handling (try-catch)
 * - File writing (FileWriter)
 * - User input (Scanner)
 */







import java.util.Scanner;
import java.io.FileWriter; // Export files
import java.io.IOException; //Try catch for files
import java.util.ArrayList;
import java.util.InputMismatchException; //Try catch



public class Movie {

    // movie variable characteristics (Private instance variables)

    private String title;
    private String genre;
    private int releaseYear;
    private boolean hasSequel;




    // Parameterized Constructor
    Movie(String title, String genre, int releaseYear, boolean hasSequel){
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.hasSequel = hasSequel;
    }


    // toString Method
    @Override
    public String toString(){
        return "[" + title + " | " + genre + " | " + releaseYear + " | Sequel: " + hasSequel + "]";

    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // User input for menu
        int option;


        // Pre-load it with 2 default movies objects
        Movie movie1 = new Movie("Inception", "Sci-Fi", 2010, false);
        Movie movie2 = new Movie("Titanic", "Romance", 1997, false);


        // ArrayList of type Movie
        ArrayList<Movie> movies = new ArrayList<>();

        // Add the two default objects
        movies.add(movie1);
        movies.add(movie2);


        // Menu
        do{
            System.out.println("\n========== COMMANDS ==========");

            
            
            System.out.println("1. View all movies");
            System.out.println("2. Add a movie");
            System.out.println("3. Remove a movie");
            System.out.println("4. Remove all movies");
            System.out.println("5. Export movies as .txt");
            System.out.println("6. Quit\n");

            System.out.print("Enter an option (1-6): ");

            try{
            option = input.nextInt();
            input.nextLine();// Clear buffer
            
            }

             catch (InputMismatchException e) {
            input.nextLine();// Clear buffer
            option = 0; // reset
            } 


            
            System.out.println();


            // Switch, Calls methods
            switch(option){
                case 1:
                    viewAllMovies(movies);
                    break;

                case 2:
                    addMovies(movies, input);
                    break;

                case 3:
                    removeMovie(movies, input);
                    break;

                case 4:
                    removeAllMovies(movies);
                    break;

                case 5:
                    exportMovies(movies);
                    break;

                case 6:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option.");
                    System.out.println();}

            

        } while(option !=6 ); // running until the user chooses “Quit” (option 6).
        input.close();


       
        
    }
    
     
        /**
         * Method
         * Purpose:View all movies method
         * @param movies
         */
        private static void viewAllMovies(ArrayList<Movie> movies){

            // If no movies in library, display the following
            if (movies.size() == 0){
            System.out.println("No movies in the library\n");
            return;}


            System.out.println("--- Movie List ---");
            System.out.println("You have " + movies.size() + " movies in your library!");

            for (Movie m : movies){
                System.out.println(m);
            }
            System.out.println();
            
            
        }

    
    /**
     * Method
     * Purpose: Add a movie
     * @param movies
     * @param input
     */
    private static void addMovies(ArrayList<Movie> movies, Scanner input){

        // If no movies in library, display the following
            if (movies.size() == 0){
            System.out.println("No movies in the library, add some!");
            System.out.println();
            }

        // parameters for the new object
        String title, genre;
        int year;
        boolean hasSequel;

        try{
        System.out.print("Enter title: ");
        title = input.nextLine();

        System.out.print("Enter genre: ");
        genre = input.nextLine();

        System.out.print("Enter release year: ");
        year = input.nextInt();

        System.out.print("Has sequel (true/false): ");
        hasSequel = input.nextBoolean();
        }

        catch(InputMismatchException e){
            System.out.println("Invalid input.");
            input.nextLine();
            return;}



        Movie newMovie = new Movie(title, genre, year, hasSequel);

        movies.add(newMovie);

        System.out.println("\nMovie added successfully: " + newMovie + "\n");

    }

    
    /**
     * Methood
     * Purpose:Remove a movie
     * @param movies
     * @param input
     */
    private static void removeMovie(ArrayList<Movie> movies, Scanner input){

        if (movies.size() == 0){
            System.out.println("No movies to remove");
            System.out.println();
            return;
        }
        // Displays movies
        System.out.println("Movie library: ");
            for (int i = 0; i < movies.size(); i++){
                System.out.print(i+ ": " + movies.get(i));
                System.out.println();
            }
            System.out.println();

        // Choose a movie to remove
        int delete;
        System.out.print("Enter the index of the movie to remove: ");

        try{
        delete = input.nextInt();
        input.nextLine();// Clear buffer
        }

        catch(InputMismatchException e){
            System.out.println("Invalid input. Please enter a number.");
            input.nextLine();
            return;
            
        }
        System.out.println();
        
        if (delete >= 0 && delete < movies.size()) {

        // Displays message that movie is succesfully deleted
        System.out.println("\nRemoving movie:");
        System.out.println("-> " + movies.get(delete));
        System.out.println();
        System.out.println();
 
        movies.remove(delete);}

        else {
         System.out.println("Invalid index.");
            }
        
    }


    
    /**
     * Method
     * Purpose: Remove all movies
     * @param movies
     */
    private static void removeAllMovies(ArrayList<Movie> movies){
        movies.clear();
        System.out.println("\nAll movies have been removed from the library.\n");
    }


    
    /**
     * Method
     * Purpose: Export movie library as .txt file
     * @param movies
     */
    private static void exportMovies(ArrayList<Movie> movies){
        try(FileWriter writer = new FileWriter("movies.txt")){
            for (Movie m : movies){
                writer.write(m + "\n");

                System.out.println("Exported!");
            }

            
            
        }

        catch(IOException e){
            System.out.println("Error exporting.");

        }


        
    }
    
}


