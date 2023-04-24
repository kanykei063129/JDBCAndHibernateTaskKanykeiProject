import peaksoft.dao.UserDaoHibernateImpl;
import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
//        userService.createUsersTable();
//        userService.dropUsersTable();
//        userService.saveUser("Kanykei","Akjoltoi kyzy", (byte) 17);
//        userService.saveUser("Madina","Halikova", (byte) 16);
//        userService.saveUser("Acema","Akjoltoi kyzy", (byte) 15);
//        userService.removeUserById(2L);
//        userService.removeUserById(3L);
//        System.out.println(userService.getAllUsers());
//        userService.cleanUsersTable();

        userDaoHibernate.createUsersTable();
    }
}
