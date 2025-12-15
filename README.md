# tanstack-micronaut
tanstack & micronaut template with automatic openapi type generation using maven, running with java 25

## Structure

- `common/` folder contains common backend java code, like authentication, common entities, its indicated you do not change anything here so you can easily merge with this repo later to benefit from new updates made to this template
- `server/` folder is your specific java backend code, its recommended you extend the other classes from common here 
- `frontend/` contains all typescript code related to frontend, it is a pnpm monorepo

## Development

```bash
# Terminal 1 - Backend
mvn mn:run

# Terminal 2 - Client
cd frontend/apps/client && pnpm run dev
```

## Features
- java backend
- user authentication
- server side side rendering and authorization
- automatic openapi type generation (work in progress)
- redis sessions
- postgresql database

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