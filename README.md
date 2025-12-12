# tanstack-micronaut
tanstack & micronaut template with automatic openapi type generation using maven, running with java 25

## Development

### Run Backend & Client in Parallel

**Option 1: Makefile (Quickest)**
```bash
make dev
```

**Option 2: Shell Script**
```bash
./dev.sh
```

**Option 3: Maven Profile**
```bash
mvn exec:exec -Pdev
```

**Option 4: Maven Parallel Profile**
```bash
mvn validate -Pdev-parallel
```

**Option 5: Separate Terminals**
```bash
# Terminal 1 - Backend
mvn mn:run

# Terminal 2 - Client
cd client && pnpm run dev
```

### Services
- **Backend**: http://localhost:8080
- **Frontend**: http://localhost:3000
- **OpenAPI**: http://localhost:8080/swagger/server-0.0.yml

## Tech Stack

### Backend
- maven
- micronaut
- flyway
- openapi
- hibernate

### Frontend
- pnpm
- react
- shadcn
- openapi-typescript
- tanstack/start

### Database
- postgresql