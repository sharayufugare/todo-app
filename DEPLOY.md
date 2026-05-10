# Todo App - Deployment Guide

## ⚠️ Note: This is a Spring Boot app — it cannot be deployed on Netlify.
Netlify only supports static sites. Use one of the platforms below.

---

## Option 1: Railway (Recommended — easiest)

1. Go to https://railway.app and sign up
2. Click "New Project" → "Deploy from GitHub repo"
3. Push your code to GitHub first, then connect the repo
4. Add a MySQL plugin: click "+ New" → "Database" → "MySQL"
5. Set these environment variables in Railway dashboard:
   - `DB_URL` = `jdbc:mysql://<railway-mysql-host>:3306/todoapp_db?useSSL=false&serverTimezone=UTC`
   - `DB_USERNAME` = (from Railway MySQL plugin)
   - `DB_PASSWORD` = (from Railway MySQL plugin)
   - `JWT_SECRET` = (any long random string, e.g. 64 hex chars)
6. Railway auto-detects Maven and runs `./mvnw clean package`

---

## Option 2: Render

1. Go to https://render.com and sign up
2. Click "New Web Service" → connect your GitHub repo
3. Set:
   - Build Command: `./mvnw clean package -DskipTests`
   - Start Command: `java -jar target/todo-app-1.0.0.jar`
4. Add environment variables (DB_URL, DB_USERNAME, DB_PASSWORD, JWT_SECRET)
5. Create a MySQL database on PlanetScale (free) or Render's own DB
   - PlanetScale: https://planetscale.com

---

## Environment Variables Required

| Variable | Description |
|---|---|
| `PORT` | Server port (auto-set by platform) |
| `DB_URL` | MySQL JDBC URL |
| `DB_USERNAME` | Database username |
| `DB_PASSWORD` | Database password |
| `JWT_SECRET` | Secret key for JWT tokens (use a long random string) |

---

## Local Development
```bash
cd todo-app
./mvnw spring-boot:run
```
App runs at http://localhost:8080
