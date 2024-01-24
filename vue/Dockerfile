# Use Alpine-based Node image for a smaller footprint
FROM node:alpine

# Set the working directory in the container
WORKDIR /srv/app

# Copy package.json and package-lock.json to the container
COPY package*.json ./

# Install project dependencies quietly
RUN npm install --silent

# Copy the rest of your app's source code from your host to your image filesystem.
COPY . .

# Expose port 5173 for Vite
EXPOSE 5173

# Run the Vite development server
CMD ["npm", "run", "dev"]