import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class MovieCollectionManager {
    public static void main(String[] args) {
        new Main();
    }

    private JFrame frame;
    private JTextField titleField, searchField;
    private JComboBox<String> genreBox;
    private JTable table;
    private JLabel countLabel;
    private LinkedList<Movie> movies;

    public Main() {
        movies = new LinkedList<>();
        frame = new JFrame("Movie Collection Manager");
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        titleField = new JTextField(10);
        genreBox = new JComboBox<>(new String[]{"action", "comedy", "horror"});
        JButton addButton = new JButton("Add Movie");
        table = new JTable(new String[][]{}, new String[]{"Title", "Genre"});
        JScrollPane scrollPane = new JScrollPane(table);
        searchField = new JTextField(10);
        JButton searchButton = new JButton("Search");
        JButton sortTitleButton = new JButton("Sort by Title");
        JButton sortGenreButton = new JButton("Sort by Genre");
        countLabel = new JLabel("Total Movies: 0");

        frame.add(new JLabel("Title:"));
        frame.add(titleField);
        frame.add(new JLabel("Genre:"));
        frame.add(genreBox);
        frame.add(addButton);
        frame.add(scrollPane);
        frame.add(new JLabel("Search:"));
        frame.add(searchField);
        frame.add(searchButton);
        frame.add(sortTitleButton);
        frame.add(sortGenreButton);
        frame.add(countLabel);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addMovie();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchMovie();
            }
        });

        sortTitleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortMoviesByTitle();
            }
        });

        sortGenreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortMoviesByGenre();
            }
        });

        frame.setVisible(true);
    }

    private void addMovie() {
        String title = titleField.getText().trim();
        String genre = (String) genreBox.getSelectedItem();
        if (!title.isEmpty()) {
            movies.add(new Movie(title, genre));
            updateTable();
        }
    }

    private void updateTable() {
        String[][] data = new String[movies.size()][2];
        for (int i = 0; i < movies.size(); i++) {
            data[i][0] = movies.get(i).getTitle();
            data[i][1] = movies.get(i).getGenre();
        }
        table.setModel(new JTable(data, new String[]{"Title", "Genre"}).getModel());
        countLabel.setText("Total Movies: " + movies.size());
    }


    private void searchMovie() {
        String searchTitle = searchField.getText().trim().toLowerCase();
        for (int i = 0; i < table.getRowCount(); i++) {
            if (table.getValueAt(i, 0).toString().toLowerCase().equals(searchTitle)) {
                table.setRowSelectionInterval(i, i);
                return;
            }
        }
    }


    private void sortMoviesByTitle() {
        for (int i = 0; i < movies.size() - 1; i++) {
            for (int j = i + 1; j < movies.size(); j++) {
                if (movies.get(i).getTitle().compareTo(movies.get(j).getTitle()) > 0) {
                    Movie l = movies.get(i);
                    movies.set(i, movies.get(j));
                    movies.set(j, l);
                }
            }
        }
        updateTable();
    }

    private void sortMoviesByGenre() {
        for (int i = 0; i < movies.size() - 1; i++) {
            for (int j = i + 1; j < movies.size(); j++) {
                if (movies.get(i).getGenre().compareTo(movies.get(j).getGenre()) > 0) {
                    Movie l = movies.get(i);
                    movies.set(i, movies.get(j));
                    movies.set(j, l);
                }
            }
        }
        updateTable();
    }
}


class Movie {
    private String title;
    private String genre;

    public Movie(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }
}
