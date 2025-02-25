CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    role_name VARCHAR(50) NOT NULL,
    role_description VARCHAR(255) NULL
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    role_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE SET NULL
);

CREATE TABLE tasks (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(50) CHECK (status IN ('Pendiente', 'En Progreso', 'Completada')) NOT NULL DEFAULT 'Pendiente',
    priority VARCHAR(50) CHECK (priority IN ('Alta', 'Media', 'Baja')) NOT NULL DEFAULT 'Baja',
    user_id INT NOT NULL,
    supervisor INT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL,
    FOREIGN KEY (supervisor) REFERENCES users(id) ON DELETE SET NULL
);

INSERT INTO roles (role_description,role_name) VALUES ('Líder Técnico','Líder Técnico'), ('Desarrollador','Desarrollador');
INSERT INTO users (name,email, role_id) VALUES ('Admin','admin@admin.com', (Select id From roles where role_name = 'Líder Técnico'));