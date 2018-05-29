import org.hibernate.Session;

import com.ikubinfo.model.one2many.Role;
import com.ikubinfo.model.one2many.User;
import com.ikubinfo.util.HibernateUtil;

public class HibernateTest {

	private static Session session;

	public static void main(String[] args) {
		init();
		session.beginTransaction();
		System.out.println("Transaction 1");
		User user = createUser();
		session.getTransaction().commit();

		session.beginTransaction();
		System.out.println("Transaction 2");
		updateUser(user);
		session.getTransaction().commit();

		System.out.println("Done");
	}

	private static User createUser() {
		User u = new User();
		u.setFirstName("Donald");
		u.setLastName("Lika");
		session.save(u);
		return u;
	}

	private static void updateUser(User u) {
		// Testing update role + adding new role cascade
		Role role = new Role();
		role.setName("NewRole");
		u.setFirstName("FirstNameTestUpdate");
		u.setRole(role);
		session.update(u);
	}

	private static void init() {
		session = HibernateUtil.getSessionFactory().openSession();
	}

}
