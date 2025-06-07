# TarmacEngine 🚀

**TarmacEngine** is a next-generation, memory-native, ACID-compliant database optimized for real-time data ingestion, streaming analytics, and semantic querying. Designed from the ground up for speed, scalability, and developer ergonomics, TarmacEngine enables constant-time ingestion, low-latency querying, and intelligent data routing between memory and disk.

---

## 🌋 Why TarmacEngine?

- 🔥 **Memory-Native Ingestion**  
  Ultra-fast ingestion into in-memory  with byte-level precision and constant-time write/overwrite.

- 📦 **Disk Persistence (Optional)**  
  Intelligent, query-aware transition to persistent storage when memory buffer is full or triggered.

- 🔁 **Streaming First**  
  Seamlessly ingest and query real-time data streams using our `MemoryStore` abstraction.

- 🧪 **Computation on Ingest**  
  Inject lightweight ML models or anomaly detectors directly into streaming ingestion pipelines.

- 💬 **Natural Language Querying (Beta)**  
  Ask questions about your data — we’ll fetch the stream, not just the table.

---

## 🔧 Architecture

- `Tarmac-core` – Core logic, memory management, ring buffer, and data models  
- `Tarmac-server` – WebSocket ingestion/query server  
- `Tarmac-client` – Java client for interacting with the ingestion API  
- `Tarmac-catalog` – Manages Vault-like metadata and semantic mappings  
- `Tarmac-disk` – Handles cold storage, Parquet chunking, and retrieval  
- `Tarmac-ml` – Plug in computation logic on streaming ingest  
- `Tarmac-nlp` – Semantic query translation and execution (optional)  

---

## 🚀 Quick Start Guide

### 🛠️ Prerequisites

- Java 17+
- Gradle 8+
- Git
- IntelliJ IDEA (recommended)

### 📥 Clone the Repository

```bash
git clone https://github.com/Tarmac-db/TarmacEngine.git
cd TarmacEngine
```

### ⚙️ Build the Project

```bash
./gradlew build
```

To build a specific module:

```bash
./gradlew :Tarmac-server:build
```

### 🚦 Run the Tarmac Server

```bash
./gradlew :Tarmac-server:run
```

The server will:
- Start the WebSocket and gRPC services
- Register incoming stream handshakes
- Begin listening for ingestion and query requests

### 🧪 Try Ingesting Data

You can use the `Tarmac-client` module to simulate a client:

```java
HandshakeRequest req = HandshakeRequest.newBuilder()
    .setClientId("client-001")
    .setStreamId("weather-stream")
    .setUseCompression(false)
    .build();

stub.handshake(req, new StreamObserver<>() {
    @Override
    public void onNext(HandshakeResponse res) {
        System.out.println("🟢 Handshake succeeded: " + res.getMessage());
    }
});
```

### 🔍 Querying (Coming Soon)

- Range-based stream queries
- `/status` endpoint
- Semantic querying 

---

## 📦 MVP Feature Set

- ✅ In-memory buffer with constant-time writes
- ✅ Threaded architecture
- ✅ WebSocket handshake and ingestion API
- ✅ Stream re-registration
- ✅ Lightweight querying of recent events
- ✅ Logs and status output for ingestion/query performance
- ⏳ Disk persistence and semantic NLP querying (WIP)

---

## 📜 License – Business Source License 1.1 (BSL 1.1)

**Licensor**: Bryan Vukania  
**Licensed Work**: TarmacEngine source code and related materials  
**Change Date**: January 1, 2028  
**Change License**: Apache License, Version 2.0

### Usage Limitation

The Licensed Work may not be used for a Commercial Purpose, except as expressly permitted by the Licensor.  
"Commercial Purpose" means the use of the Licensed Work:

- to earn revenue,
- in a commercial product or service,
- or for internal business operations.

Non-commercial use is permitted under this license until the Change Date. After the Change Date, the Licensed Work will automatically convert to the Change License.

📖 Full license text: [https://mariadb.com/bsl11](https://mariadb.com/bsl11)

---

## 👤 Author

### Bryan Vukania

- **Role**: Founder & Principal Engineer  
- **GitHub**: [@bryanvukania](https://github.com/V-Ghost)  
- **LinkedIn**: [linkedin.com/in/bryanvukania](https://www.linkedin.com/in/bryan-vukania-92a3b4210/)  
- **Location**: London, United Kingdom  


---

## 🤝 Contributors

Want to contribute? Submit a pull request or open an issue!

To be listed here, make a meaningful contribution and add yourself to this file:

```bash
# In your pull request
- name: Bryan Vukania
  github: @V-Ghost
  contribution: Founder
```

---

> Built for those who demand performance, flexibility, and insight — not just storage.
