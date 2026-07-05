# i2i Academy - Spring Boot GCP Deployment Project

This repository contains a Java Spring Boot application successfully deployed to a Google Cloud Platform (GCP) Compute Engine virtual machine instance.

## 🚀 Project Features and Endpoint
- **Framework:** Spring Boot v4.1.0
- **Java Version:** OpenJDK 17
- **Main Endpoint:** `http://<GCP-VM-PUBLIC-IP>:8080/spring-boot`
- **Response:** `Welcome to i2i Academy! Mustafa Oskay! Spring Boot`

---

## 🛠️ Infrastructure and Deployment Process

The process of transferring data to the GCP Linux instance involved addressing various infrastructure constraints and implementing solutions suitable for a production environment deployment:

1. **Privilege Escalation (Sudo Restriction):** The default user account lacked administrative access. This was resolved by adding a persistent **Startup Script** via the GCP Console to ensure secure `root` execution privileges.
2. **Network/Internet Isolation:** The cloud instance had restricted access to external download sites (blocking `wget` commands). To overcome this, the executable JAR file was securely uploaded to a **Google Cloud Storage (GCS) Bucket** and successfully retrieved by the instance using internal infrastructure connections via the `gcloud storage cp` command.
3. **Port Conflict Management:** Port 8080 was initially blocked by an active, legacy background process. The relevant PID (`7232`) was dynamically identified using process monitoring tools (`ps -ef | grep java`) and safely terminated via `kill -9`, thereby fully releasing the network port held by the Tomcat server.

---

## 🏃 Running on a Cloud Virtual Machine (VM)

Once the environment was fully optimized, the application was launched with full `root` privileges: