package info.source4code.jsf.primefaces.model;

public class CandidateProfile {

	public String profilePictureString = null;
	public String username = null;
	public String password = null;
	public String jobTitle = null;

	public String company = null;
	public String firstName;
	public String lastName;
	public String email;

	public String getFirstName() {
		return firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getProfilePictureString() {
		return profilePictureString;
	}

	public void setProfilePictureString(String profilePictureString) {
		this.profilePictureString = profilePictureString;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}
