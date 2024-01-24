import axios  from 'axios'

export const fetchAllMessages = () => {
    return new Promise((resolve, reject) =>
        axios
            .get('http://localhost:8080/all')
            .then((res) => resolve(res.data))
            .catch((err) => reject(err))
    );
}

export const createMessage = (message) => {
    return axios.post('http://localhost:8080/new', { message }, {
      headers: {
        'Content-Type': 'application/json',
      },
    });
}