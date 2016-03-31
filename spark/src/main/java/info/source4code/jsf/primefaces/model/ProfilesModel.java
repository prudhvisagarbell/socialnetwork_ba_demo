package info.source4code.jsf.primefaces.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProfilesModel implements Serializable {

    private static final long serialVersionUID = 1L;

  
   private List<CandidateProfile> candidateProfiles = new ArrayList<CandidateProfile>();


public List<CandidateProfile> getCandidateProfiles() {
	return candidateProfiles;
}


public void setCandidateProfiles(List<CandidateProfile> candidateProfiles) {
	this.candidateProfiles = candidateProfiles;
}

   
}
