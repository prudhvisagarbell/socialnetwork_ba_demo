package info.source4code.jsf.primefaces.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import info.source4code.jsf.primefaces.controller.dao.LoginDAO;
import info.source4code.jsf.primefaces.model.CandidateProfile;
import info.source4code.jsf.primefaces.model.ProfilesModel;
import info.source4code.jsf.primefaces.model.UploadFile;
import info.source4code.jsf.primefaces.model.User;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.apache.commons.codec.binary.Base64;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class UserManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserManager.class);

	public static final String HOME_PAGE_REDIRECT = "/secured/home.xhtml?faces-redirect=true";
	public static final String LOGOUT_PAGE_REDIRECT = "/logout.xhtml?faces-redirect=true";

	private String userId;
	private String userPassword;
	private User currentUser;
	private UploadFile uploadedFile;
	public boolean loginDialog;
	public int i;
	private List<CandidateProfile> profiles;

	public UploadFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	private String filename;

	public boolean isLoginDialog() {
		return loginDialog;
	}

	public void setLoginDialog(boolean loginDialog) {
		this.loginDialog = loginDialog;
	}

	public CandidateProfile candidateProfile = new CandidateProfile();

	public CandidateProfile getCandidateProfile() {
		return candidateProfile;
	}

	public void setCandidateProfile(CandidateProfile candidateProfile) {
		this.candidateProfile = candidateProfile;
	}

	public String getFilename() {
		return filename;
	}

	@PostConstruct
	public void init() {
		setLoginDialog(true);
		
		profiles = searchProfiles("12").getCandidateProfiles();
		
		
		setProfiles(profiles);
System.out.println("profiles"+profiles.size());
		// CandidateProfile canProfile = new CandidateProfile("asdasd",
		// "asdasd", "asdasd", "asdasd", "asdasd", "asdasd", "asdasd",
		// "asdasd");
		// profiles.add(canProfile);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		if (session.getAttribute("username") != null)
			this.setLoginDialog(false);
	}

	public List<CandidateProfile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<CandidateProfile> profiles) {
		this.profiles = profiles;
	}

	public String login() {
		// lookup the user based on user name and user password
		currentUser = find(userId, userPassword);

		if (currentUser != null) {
			LOGGER.info("login successful for '{}'", userId);
			this.setLoginDialog(false);
			return HOME_PAGE_REDIRECT;
		} else {
			LOGGER.info("login failed for '{}'", userId);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Login failed", "Invalid or unknown credentials."));

			return null;
		}
	}

	public ProfilesModel searchProfiles(String searchParameter) {

		List<Object> providers = new ArrayList<Object>();
		providers.add(new JacksonJsonProvider());

		WebClient client = WebClient.create("http://localhost:8080/restws/services/patientservice/patients/123",
				providers);

		client = client.accept("application/json").type("application/json");

		Response r = client.get();
		ProfilesModel resp = r.readEntity(ProfilesModel.class);
		System.out.println(resp);

		return resp;

	}

	public void singlePageLogin() {
		// lookup the user based on user name and user password
		currentUser = find(userId, userPassword);

		if (currentUser != null) {
			LOGGER.info("login successful for '{}'", userId);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
			session.setAttribute("username", userId);
			RequestContext.getCurrentInstance().execute("PF('loginandregisterdlg').hide()");
			// RequestContext.getCurrentInstance().execute("loginandregisterdlg.hide()");
			// RequestContext.getCurrentInstance().closeDialog("loginandregisterdlg");

		} else {
			LOGGER.info("login failed for '{}'", userId);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Login failed", "Invalid or unknown credentials."));

		}
	}

	public void singlePageRegister() {
		System.out.println(candidateProfile);
		if (candidateProfile.password != null) {
			/*
			 * byte[] abValue = {...}; // Your data to encode Base64 base64 =
			 * new Base64(); String strEncodedData =
			 * base64.encodeToString(abValue).trim();
			 * 
			 * 
			 * 
			 * String strEncodedData = "..."; // Your previously encoded data
			 * Base64 base64 = new Base64(); byte[] abValue =
			 * base64.decode(strValue);
			 * 
			 */

			/*
			 * // Reading a Image file from file system FileInputStream
			 * imageInFile = new FileInputStream(file); byte imageData[] = new
			 * byte[(int) file.length()]; imageInFile.read(imageData);
			 * 
			 * // Converting Image byte array into Base64 String String
			 * imageDataString = encodeImage(imageData);
			 * 
			 * // Converting a Base64 String into Image byte array byte[]
			 * imageByteArray = decodeImage(imageDataString);
			 * 
			 * // Write a image byte array into file system FileOutputStream
			 * imageOutFile = new FileOutputStream(
			 * "/Users/jeeva/Pictures/wallpapers/water-drop-after-convert.jpg");
			 * 
			 * imageOutFile.write(imageByteArray);
			 * 
			 * imageInFile.close(); imageOutFile.close();
			 */

			// byte imageData[] = new byte[(int)
			// candidateProfile.getUploadedImage().getSize()];
			// imageInFile.read(imageData);

			// String imageDataString = encodeImage(imageData);
			candidateProfile.setProfilePictureString(encodeImage(uploadedFile.getContents()));

			// Converting Image byte array into Base64 String
			// String imageDataString = encodeImage(imageData);

			try {
				ClientConfig clientConfig = new DefaultClientConfig();
				clientConfig.getFeatures().put(

						JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
				Client client = Client.create(clientConfig);
				WebResource webResource = client

						.resource("http://localhost:8080/restws/services/patientservice/patients");
				ClientResponse response = webResource.accept("application/json").type("application/json")
						.post(ClientResponse.class, candidateProfile);
				if (response.getStatus() != 200) {
					throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());

				}
				String output = response.getEntity(String.class);
				System.out.println("Server response .... \n");
				System.out.println(output);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Encodes the byte array into base64 string
	 *
	 * @param imageByteArray
	 *            - byte array
	 * @return String a {@link java.lang.String}
	 */
	public static String encodeImage(byte[] imageByteArray) {
		return Base64.encodeBase64URLSafeString(imageByteArray);
	}

	/**
	 * Decodes the base64 string into byte array
	 *
	 * @param imageDataString
	 *            - a {@link java.lang.String}
	 * @return byte array
	 */
	public static byte[] decodeImage(String imageDataString) {
		return Base64.decodeBase64(imageDataString);
	}

	public String logout() {
		String identifier = userId;

		// invalidate the session
		LOGGER.debug("invalidating session for '{}'", identifier);
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		LOGGER.info("logout successful for '{}'", identifier);
		return LOGOUT_PAGE_REDIRECT;
	}

	public void handleFileUpload(FileUploadEvent event) {
		try {
			// workaround for event.getFile().getContents() returning NULL
			byte[] contents = IOUtils.toByteArray(event.getFile().getInputstream());

			UploadFile uploadedFile = new UploadFile(event.getFile().getFileName(), event.getFile().getContentType(),
					event.getFile().getSize(), contents);
			// this.candidateProfile.setUploadedImage(uploadedFile);
			this.uploadedFile = uploadedFile;

			LOGGER.debug("uploaded: {}", uploadedFile);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"File uploaded", event.getFile().getFileName() + " is successfuly uploaded"));
		} catch (IOException ioException) {
			LOGGER.error("file not uploaded", ioException);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Upload Failed", event.getFile().getFileName() + " is not uploaded"));
		}
	}

	public boolean isLoggedIn() {
		return currentUser != null;
	}

	public String isLoggedInForwardHome() {
		if (isLoggedIn()) {
			return HOME_PAGE_REDIRECT;
		}

		return null;
	}

	private User find(String userId, String password) {
		User result = null;

		// code block to be replaced with actual retrieval of user
		/*
		 * if ("john.doe".equalsIgnoreCase(userId) && "1234".equals(password)) {
		 * result = new User(userId, "John", "Doe"); }
		 */
		if (LoginDAO.validate(userId, password)) {
			result = new User(userId, userId, userId);
		}

		return result;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	// do not provide a setter for currentUser!
}
