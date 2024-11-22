package view;

import controller.ContactController;
import model.Contact;

import java.util.List;
import java.util.Scanner;

public class MainView {
    public static void main(String[] args) {
        ContactController contactController = new ContactController();
        List<Contact> contacts;
        Contact contact;
        while (true) {
            System.out.println("----CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ----");
            System.out.println("Chọn chức năng theo số (để tiếp tục)");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Tìm kiếm");
            System.out.println("6. Đọc từ file");
            System.out.println("7. Ghi vào file");
            System.out.println("8. Thoát");
            int choice = inputChoice();
            switch (choice) {
                case 1:
                    System.out.println("Hiển thị danh sách");
                    contacts = contactController.getAll();
                    displayContact(contacts);
                    break;
                case 2:
                    System.out.println("Thêm mới");
                    contact = inputContact();
                    contactController.add(contact);
                    System.out.println("Thêm mới thành công");
                    break;
                case 3:
                    System.out.println("Cập nhật");
                    updateContact();
                    break;
                case 4:
                    System.out.println("Xóa");
                    deleteContact();
                    break;
                case 5:
                    System.out.println("Tìm kiếm");
                    System.out.print("Nhập số điện thoại muốn tìm: ");
                    int phone3 = inputPhoneToFind();
                    if (contactController.isExist(phone3)){
                        System.out.println(contactController.findByPhone(phone3));
                    } else {
                        System.out.println("Không tìm thấy số điện thoại");
                    }
                    break;
                case 6:
                    System.out.println("Đọc từ file");
                    break;
                case 7:
                    System.out.println("Ghi vào file");
                    break;
                default:
                    break;
            }
        }
    }

    private static void deleteContact() {
        ContactController contactController = new ContactController();
        System.out.print("Mời bạn nhập số điện thoại muốn xóa: ");
        int phone2 = inputPhoneToFind();
        if (contactController.isExist(phone2)){
            System.out.println("Bạn có chắc chắn muốn xóa?");
            System.out.println(contactController.findByPhone(phone2));
            System.out.print("Chọn số 1 để xóa, phím bất kỳ để hủy");
            int choice2 = inputChoice();
            if (choice2 == 1){
                contactController.deleteByPhone(phone2);
                System.out.println("Đã xóa thành công");
            } else {
                System.out.println("Bạn đã không xóa");
            }
        }
        else {
            System.out.println(" Không tìm thấy số điện thoại");
        }
    }

    private static void updateContact() {
        Scanner scanner = new Scanner(System.in);
        ContactController contactController = new ContactController();
        System.out.print("Mời bạn nhập số điện thoại: ");
        int phone = inputPhoneToFind();
        System.out.println(contactController.findByPhone(phone));
        if (contactController.isExist(phone)) {
            System.out.print("Mời bạn nhập nhóm của danh bạ: ");
            String group = scanner.nextLine();
            while (!(group.matches("^[A-Za-z0-9 -]+$"))){
                System.out.println("Bạn nhập sai định dạng");
                System.out.print("Vui lòng nhập lại nhóm danh bạ: ");
                group = scanner.nextLine();
            }

            System.out.print("Mời bạn nhập tên: ");
            String name = scanner.nextLine();
            while (!(name.matches("^[A-Za-z ]+$"))){
                System.out.println("Bạn nhập sai định dạng");
                System.out.print("Vui lòng nhập lại tên: ");
                name = scanner.nextLine();
            }

            System.out.print("Mời bạn nhập giới tính (Nam/Nữ): ");
            String gender = scanner.nextLine();

            System.out.print("Mời bạn nhập điạ chỉ: ");
            String address = scanner.nextLine();

            System.out.print("Mời bạn nhập ngày sinh: ");
            String date = scanner.nextLine();

            System.out.print("Mời bạn nhập Email: ");
            String email = scanner.nextLine();

            Contact contact = new Contact(phone, group, name, gender, address, date, email);
            contactController.update(contact);
        } else {
            System.out.println("Không tìm thấy số điện thoại");
        }
    }

    private static int inputPhoneToFind() {
        Scanner scanner = new Scanner(System.in);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.print("Nhập sai, vui lòng nhập lại: ");
        }
        return 0;
    }

    private static Contact inputContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Mời bạn nhập số điện thoại: ");
        int phone = inputNumber();
        while (phone < 0) {
            phone = inputNumber();
        }

        System.out.print("Mời bạn nhập nhóm của danh bạ: ");
        String group = scanner.nextLine();
        while (!(group.matches("^[A-Za-z0-9 -]+$"))){
            System.out.println("Bạn nhập sai định dạng");
            System.out.print("Vui lòng nhập lại nhóm danh bạ: ");
            group = scanner.nextLine();
        }

        System.out.print("Mời bạn nhập tên: ");
        String name = scanner.nextLine();
        while (!(name.matches("^[A-Za-z ]+$"))){
            System.out.println("Bạn nhập sai định dạng");
            System.out.print("Vui lòng nhập lại tên: ");
            name = scanner.nextLine();
        }

        System.out.print("Mời bạn nhập giới tính (Nam/Nữ): ");
        String gender = scanner.nextLine();

        System.out.print("Mời bạn nhập điạ chỉ: ");
        String address = scanner.nextLine();

        System.out.print("Mời bạn nhập ngày sinh: ");
        String date = scanner.nextLine();

        System.out.print("Mời bạn nhập Email: ");
        String email = scanner.nextLine();

        return new Contact(phone, group, name, gender, address, date, email);
    }

    private static int inputNumber() {
        Scanner scanner = new Scanner(System.in);
        ContactController contactController = new ContactController();
        try {
            int phone = Integer.parseInt(scanner.nextLine());
            if (contactController.isExist(phone)){
                System.out.print("Số điện thoại đã tồn tại, vui lòng nhập lại: ");
                return 0;
            } else {
                return phone;
            }
        } catch (NumberFormatException e) {
            System.out.print("Nhập sai, vui long nhập lại: ");
        }
        return 0;
    }

    private static void displayContact(List<Contact> contacts) {
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    private static int inputChoice() {
        Scanner sc = new Scanner(System.in);
        int choice;
        try {
            choice = Integer.parseInt(sc.nextLine());
            return choice;
        } catch (NumberFormatException e) {
            System.out.print("Nhập sai lựa chọn, vui lòng nhập lại: ");
        } catch (Exception e) {
            System.out.println("Lỗi khác");
        }
        return 0;
    }
}
