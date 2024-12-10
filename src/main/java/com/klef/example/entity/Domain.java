package com.klef.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class Domain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Size(min = 20, max = 65535, message = "Description cannot exceed 65,535 characters")
    @Column(columnDefinition = "TEXT")
    private String description;


   

    @Column(nullable = true)
    private String demoVideo; // URL of the demo video

    @Column(nullable = true)
    private String roadmap; // URL of the roadmap image

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "domain_resources", joinColumns = @JoinColumn(name = "domain_id"))
    @Column(name = "resource_link")
    private List<String> resourcesLinks;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "domain_certifications", joinColumns = @JoinColumn(name = "domain_id"))
    @Column(name = "certification_image")
    private List<String> certifications;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "program_id", nullable = false)
    private Program program;
    @ManyToOne
    private Counsellor counselor;
    @OneToMany(mappedBy = "domain")
    private List<CounselorDomainAssignment> assignments;

    
    public Counsellor getCounselor() {
		return counselor;
	}

	public void setCounselor(Counsellor counselor) {
		this.counselor = counselor;
	}

	// Getters and setters
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDemoVideo() {
        return demoVideo;
    }

    public void setDemoVideo(String demoVideo) {
        this.demoVideo = demoVideo;
    }

    public String getRoadmap() {
        return roadmap;
    }

    public void setRoadmap(String roadmap) {
        this.roadmap = roadmap;
    }

    public List<String> getResourcesLinks() {
        return resourcesLinks;
    }

    public void setResourcesLinks(List<String> resourcesLinks) {
        this.resourcesLinks = resourcesLinks;
    }

    public List<String> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<String> certifications) {
        this.certifications = certifications;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}
