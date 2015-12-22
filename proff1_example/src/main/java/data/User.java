package data;

import java.util.Set;
import javax.persistence.UniqueConstraint;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "users", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id"),
		@UniqueConstraint(columnNames = "employee_id") })
public class User {
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "login")
	private String login;

	@Column(name = "pass")
	private String pass;
	
	@Column(name = "employee_id")
	private Long employee_id;
	
	@OneToOne
	@JoinTable(
	      name = "employees",
	      joinColumns = @JoinColumn(name = "id"),
	      inverseJoinColumns = @JoinColumn(name = "id")
	    )
	 private Employee employee;
	
	/*
	@OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	//@PrimaryKeyJoinColumn	
	//@MapsId
	@JoinTable(name = "employee")
	private Employee employee;
	*/
	
	/*
	// Unknown column 'user0_1_.employee_id' in 'on clause'
	@OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinTable(name="employees",
			joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "id")
	)
	private Employee employee;
	*/
	/* 
	 //Referenced property not a (One|Many)ToOne: data.Employee.id in mappedBy of data.User.employee
	@OneToOne(mappedBy = "id", cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id")
	private Employee employee=null;
	*/
	
	/*
	//Repeated column in mapping for entity: data.User column: employee_id (should be mapped with insert="false" update="false")
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "employee_id")
	private Employee employee=null;
	*/
	
	
		/*
	@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "employee_id")
	 @JoinTable(
	            name = "employees",
	            joinColumns = @JoinColumn(name = "user_id"),
	            inverseJoinColumns = @JoinColumn(name = "id")
	    )
	  */  
	/*
	  @JoinTable(
            name = "CATEGORY_ARTICLE",
            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "ARTICLE_ID")
    )
	 * */
	//private Employee employee=null;
	
	public Long getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	@OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinTable(name="roles_users",
			joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Role> roles;
	
	public User(Long id, String name, String pass, Employee employee) {
		this.id = id;
		this.name = name;
		this.pass = pass;
		//this.employee = employee;
	}

	public User() {
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	

//	public Employee getEmployee() {
//		return employee;
//	}
//
//	public void setEmployee(Employee employee) {
//		this.employee = employee;
//	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", login=" + login + ", pass=" + pass 
				 +", roles="+roles+ " employee_id="+employee_id+ " employee="+employee + "]";
	}

}
