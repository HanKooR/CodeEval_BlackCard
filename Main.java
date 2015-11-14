package codeeval_BlackCard;

/*
 * Copyright (C) 2015 hankoor
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 Challenge Description:

 You must have heard about pirates, their customs, pirates code, and the 
 “black spot”. If a pirate is presented with a “black spot”, he is officially 
 pronounced guilty, meaning he will soon be expulsed from the pirate brotherhood 
 or even be dead.
 We don’t have as strict rules as pirates have, and a person who receives a 
 black spot simply leaves the game.
 For example, we have a list of three players: John, Tom, Mary, and a number 5.
 Starting with the first player (in our case, it’s John), we start to count all 
 players: John – 1, Tom – 2, Mary – 3, and then again starting from the first one
 John – 4, Tom – 5. As Tom gets number 5, he should leave. Now, we have John and 
 Mary and start counting again. John gets number 5, so he leaves. Thus, 
 the winner is Mary.

 The first argument is a path to a file. Each line includes a test case with names of players and a number for a “black spot”. Players and a number are separated by a pipeline '|'. 
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author hankoor
 * @version 1.0
 */
public class Main {

    /**
     *
     * @param fileName
     */
    private static void readLinesOfFile(String fileName) {
        File file = new File(fileName);

        if (!file.canRead() || !file.isFile()) {
            System.exit(0);
        }
        BufferedReader in = null;
        String row;
        try {
            in = new BufferedReader(new FileReader(fileName));

            while ((row = in.readLine()) != null) {
                processString(row);
            }

        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ioEx) {
                    ioEx.printStackTrace();
                }
            }
        }
    }

    /**
     *
     * @param str
     */
    public static void processString(String str) {
        ArrayList<String> names;
        int countGiven = Integer.parseInt(str.split("\\|")[1].trim());
        names = new ArrayList(Arrays.<String>asList(str.split("\\|")[0].split(" ")));

        while (names.size() > 1) {
            if (names.size() >= countGiven) {
                names.remove(countGiven - 1);
            } else {
                if (countGiven % names.size() > 0) {
                    names.remove((countGiven % names.size()) - 1);
                } else {
                    names.remove(names.size() - 1);
                }
            }
        }
        System.out.println(names.get(0));

    }

    /**
     *
     * @param pathToFile File to process. Given as file-parameter.
     */
    public static void main(String[] pathToFile) {
        if ((pathToFile != null) && (pathToFile.length == 1) && (!pathToFile[0].isEmpty())) {
            readLinesOfFile(pathToFile[0]);
        }
    }
}
