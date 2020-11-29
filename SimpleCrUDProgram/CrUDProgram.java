package javaRash.Lev5.CrUDProgram;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CrUDProgram {
    public static void main(String[] args) {
        String fileName = readFileName();
        
        switch (args[0]) {
            case "c": {
                System.out.println("Add product");
                String id = searchMaxId(fileName);
                String newDate = String.format("\n%-8s%-30s%-8s%-4s", id, args[1], args[2], args[3]);
                outputDate(fileName, newDate);
                break;
            }
            case "u":{
                System.out.println("Update product");
                String newDate = String.format("%-8s%-30s%-8s%-4s", args[1], args[2], args[3], args[4]);
                List<String> inputDate = inputDate(fileName, args[1], newDate);
                outputDate(fileName, inputDate);
                return;
            }
            case "d": {
                System.out.println("Delete product");
                List<String> inputDate = inputDate(fileName, args[1]);
                outputDate(fileName, inputDate);
                return;
            }
            default: {
                System.out.println("Parametr is false");
                return;
            }
        }
    }
    private static String readFileName() {
        String fileName = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            fileName = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    private static List inputDate(String fileName, String id) {
        List<String> listDate = new ArrayList<>();

        try (BufferedReader inputStream = new BufferedReader(new FileReader(fileName))) {
            while (true) {
                String result = inputStream.readLine();
                if (result == null) break;

                if ((result.substring(0, 8).trim()).equals(id)) continue;
                else listDate.add(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listDate;
    }

    private static String searchMaxId(String fileName) {
        int maxId = 0;

        try (BufferedReader inputStream = new BufferedReader(new FileReader(fileName))) {
            while (true) {
                String result = inputStream.readLine();
                if (result == null) break;

                String stringId = result.substring(0, 8).trim();
                int intId = 0;

                try {
                    intId = Integer.parseInt(stringId);
                } catch (NumberFormatException e) {
                }
                if (maxId < intId) maxId = intId;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return String.valueOf(maxId+1);
    }
    private static synchronized void outputDate(String fileName, String newDate) {

        try (BufferedWriter outputStream = new BufferedWriter(new FileWriter(fileName, true))) {
            outputStream.write(newDate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List inputDate(String fileName, String id, String date) {
        List<String> listDate = new ArrayList<>();

        try (BufferedReader inputStream = new BufferedReader(new FileReader(fileName))) {
            while (true) {
                String result = inputStream.readLine();
                if (result == null) break;

                if ((result.substring(0, 8).trim()).equals(id)) listDate.add(date);
                else listDate.add(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listDate;
    }
    private static synchronized void outputDate(String fileName, List list) {
        try (BufferedWriter outputStream = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < list.size(); i++) {
                if (i == list.size()-1) outputStream.write((String) list.get(i));
                else outputStream.write((String) list.get(i) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}