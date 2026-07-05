# i2i Academy - Spring Boot GCP Dağıtım Projesi

Bu depo, Google Cloud Platform (GCP) Compute Engine sanal makine örneğine başarıyla dağıtılmış bir Java Spring Boot uygulamasını içermektedir.

## 🚀 Proje Özellikleri ve Uç Nokta (Endpoint)
- **Framework:** Spring Boot v4.1.0
- **Java Sürümü:** OpenJDK 17
- **Ana Uç Nokta:** `http://<GCP-VM-PUBLIC-IP>:8080/spring-boot`
- **Yanıt:** `Welcome to i2i Academy! Mustafa Oskay! Spirng Boot`

---

## 🛠️ Altyapı ve Dağıtım Süreci

GCP Linux örneği üzerindeki dağıtım sürecinde, çeşitli teknik altyapı zorlukları ele alınmış ve canlı ortam standartlarına uygun çözümlerle giderilmiştir:

1. **Yetki Yükseltme (Sudo Kısıtlaması):** Varsayılan kullanıcı hesabı yönetici erişimine sahip değildi. Bu durum, güvenli `root` çalıştırma hakları sağlamak amacıyla GCP Konsolu üzerinden kalıcı bir Başlangıç ​​Betiği (Startup Script) eklenerek çözüldü.
2. **Ağ/İnternet İzolasyonu:** Bulut örneğinin harici indirme sitelerine erişimi kısıtlanmıştı (`wget` engelleri). Bu engeli aşmak için, çalıştırılabilir JAR dosyası güvenli bir şekilde bir **Google Cloud Storage (GCS) Bucket**'ına yüklendi ve `gcloud storage cp` komutuyla dahili altyapı kanalları kullanılarak örneğe başarıyla çekildi.
3. **Port Çakışması Yönetimi:** 8080 numaralı port, başlangıçta aktif ve eski bir arka plan süreci tarafından engellenmişti. Süreç filtreleme araçları (`ps -ef | grep java`) kullanılarak ilgili PID (`7232`) dinamik olarak tespit edildi ve `kill -9` komutuyla güvenli bir şekilde sonlandırılarak, ağ portu gömülü Tomcat sunucusu için tamamen serbest bırakıldı.

---

## 🏃 Bulut Sanal Makinesinde (VM) Çalıştırma

Ortam tamamen optimize edildikten sonra, uygulama tam `root` yetkileri kullanılarak başlatıldı: