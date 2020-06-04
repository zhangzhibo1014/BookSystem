import Vue from 'vue';
import Vuex from 'vuex';
import persistedState from 'vuex-persistedstate'
Vue.use(Vuex);


const store = new Vuex.Store({
    state: {
        bookInfo: []
    },
    mutations: {
        save_bookInfo(state, data) {
            state.bookInfo = data;
        }
    },
    getters: {
        bookTodos: state => {
            return state.bookInfo;
        }
    },
    plugins: [persistedState({
        storage: window.sessionStorage
    })]
});

export default store;