import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ikubinfo.model.many2many.Post;
import com.ikubinfo.model.many2many.Tag;
import com.ikubinfo.model.one2many.Role;
import com.ikubinfo.model.one2many.User;
import com.ikubinfo.model.one2one.Student;
import com.ikubinfo.model.one2one.StudentCard;

public class JpaTest {

	private static EntityManager entityManager;

	private static EntityManagerFactory entityManagerFactory;

	public static void main(String[] args) {
		init();

		// ----------------------------------------------//
		entityManager.getTransaction().begin();
		System.out.println("Transaction 1");
		// User user = createUser();
		entityManager.getTransaction().commit();

		// ----------------------------------------------//
		entityManager.getTransaction().begin();
		System.out.println("Transaction 2");
		// updateUser(user);
		entityManager.getTransaction().commit();

		// ----------------------------------------------//
		entityManager.getTransaction().begin();
		System.out.println("Transaction 3");
		// createStudent();
		entityManager.getTransaction().commit();

		// ----------------------------------------------//
		entityManager.getTransaction().begin();
		System.out.println("Transaction 4");
		createPost();
		entityManager.getTransaction().commit();

		System.out.println("Done");
	}

	private static User createUser() {
		User user = new User();
		user.setFirstName("Donald");
		user.setLastName("Lika");
		entityManager.persist(user);
		return user;
	}

	private static void createStudent() {

		Student student = new Student();
		student.setName("Donald");

		entityManager.persist(student);

		StudentCard card = new StudentCard();
		card.setCardIdNumber("NumberTest123");
		card.setStudent(student);
		entityManager.persist(card);

	}

	private static void createPost() {
		Post post = new Post();
		post.setTitle("Post 1");
		entityManager.persist(post);
		Tag tag = new Tag();
		tag.setName("TAg1");
		post.addTag(tag);
		entityManager.merge(post);
	}

	private static void updateUser(User user) {
		// Testing update role + adding new role cascade
		Role role = new Role();
		role.setName("NewRole");
		user.setFirstName("FirstNameTestUpdate");
		user.setRole(role);
		entityManager.merge(user);
	}

	private static void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory("JPATest");
		entityManager = entityManagerFactory.createEntityManager();
	}

}
