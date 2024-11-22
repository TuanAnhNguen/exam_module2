package repository;

import model.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository {
    public static final String SRC_CONTACTS = "src/data/Contact.csv";

    public List<Contact> getAll() {
        List<Contact> contacts = new ArrayList<>();
        File file = new File(SRC_CONTACTS);
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            Contact contact;
            while ((line = bufferedReader.readLine()) != null) {
                String[] temp = line.split(",");
                contact = new Contact(Integer.parseInt(temp[0]), temp[1],temp[2],temp[3],temp[4],temp[5],temp[6]);
                contacts.add(contact);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy file");
        } catch (IOException e) {
            System.out.println("Lỗi đọc file");
        }
        return contacts;
    }

    public void add(Contact c) {
        File file = new File(SRC_CONTACTS);
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(c.getPhone() + "," + c.getGroup() + "," + c.getName() + "," + c.getGender() + "," + c.getAddress() + "," + c.getDate() + "," + c.getEmail());
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy file");
        } catch (IOException e) {
            System.out.println("Lỗi ghi file");
        }
    }

    public void deleteByPhone(int phone) {
        List<Contact> contacts = getAll();
        for (Contact c : contacts) {
            if (c.getPhone() == phone) {
                contacts.remove(c);
                break;
            }
        }
        save(contacts);
    }

    public Contact findByPhone(int phone) {
        List<Contact> contacts = getAll();
        for (Contact c : contacts) {
            if (c.getPhone() == phone) {
                return c;
            }
        }
        return null;
    }

    public static void save(List<Contact> contacts) {
        File file = new File(SRC_CONTACTS);
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Contact c : contacts) {
                bufferedWriter.write(c.getPhone() + "," + c.getGroup() + "," + c.getName() + "," + c.getGender() + "," + c.getAddress() + "," + c.getDate() + "," + c.getEmail());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Lỗi ghi file");
        }
    }

    public void update(Contact contact) {
        List<Contact> contacts = getAll();
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getPhone() == contact.getPhone()) {
                contacts.set(i, contact);
                break;
            }
        }
        save(contacts);
    }

    public List<Contact> findByName(String name) {
        return null;
    }
}
