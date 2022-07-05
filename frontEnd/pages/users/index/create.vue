<template>
  <div class="flex flex-wrap w-full">
    <Modal
      :title="'Adicionar novo funcionario'"
      size="xl"
      @close="$router.push({ name: 'users' })"
    >
      <div class="grid grid-cols-3 gap-4 space-y-px w-max">
        <TextInput
          label="Username"
          placeholder="Username"
          class="col-span-3"
          required
        />
        <TextInput label="Senha" placeholder="Senha" class="" required />
        <TextInput
          label="Repetir senha"
          placeholder="Repetir senha"
          class=""
          required
        />
        <p></p>
        <TextInput
          label="Nome"
          placeholder="Nome"
          class=""
          required
        />
        <TextInput
          label="Email"
          placeholder="Email"
          class=""
          required
        />
        <TextInput
          label="Telefone"
          placeholder="82/83/84/85/86/87"
          class=""
          required
        />
        <TextInput
          label="Endereco"
          placeholder="Eduardo Mondlane"
          class=""
          required
        />
        <SelectInput
          label="Genero"
          placeholder=""
          :items="categoriesHeaders"
          class=""
          required
        />
        <SelectInput
          label="Estado Civil"
          placeholder=""
          :items="categoriesHeaders"
          class=""
          required
        />
        <SelectInput
          label="Categoria"
          placeholder=""
          :items="categories"
          :headers="categoriesHeaders"
          class=""
          required
        />
        <SelectInput
          label="Departamento"
          :headers="categoriesHeaders"
          :items="categoriesHeaders"
          placeholder=""
          class=""
          required
        />
        <SelectInput
          label="Projecto"
          :items="categoriesHeaders"
          placeholder=""
          class=""
          required
        />

      </div>
      <template #actions>
        <div class="flex items-center space-x-4">
          <AppButton
            class="flex text-white w-full h-10"
            label="Salvar"
            variant="green"
            @click.native="handleSubmit"
          />
          <AppButton
            class="flex text-black"
            label="Cancelar"
            variant="white"
            size="large"
            @close="$router.push({ name: 'users' })"
          />
        </div>
      </template>
    </Modal>
  </div>
</template>

<script lang="ts">
import Modal from "~/components/common/misc/Modal.vue";
import SelectInput from "~/components/common/inputs/SelectInput.vue";
import AppButton from "~/components/common/misc/AppButton.vue";
import TextInput from "~/components/common/inputs/TextInput.vue";
import { defineComponent } from "@nuxtjs/composition-api";

export default defineComponent({
  components: {Modal, AppButton, TextInput, SelectInput},
  data: () => ({
    data:[],
    selectedCategory: {},
    categoriesHeaders: [
      {
        key: "description",
        title: "DESCRICAO",
        class: "whitespace-no-wrap",
      },
      {
        key: "salary",
        title: "salario",
        class: "whitespace-no-wrap",
      },
    ],
  }),

  // created() {
  //   this.fetch()
  // },

  computed: {
    categories() {
      console.log("Testando " + this.$store.state.categories.data);
      return this.$store.state.categories.data;
    },
  },

  methods: {
    async fetch() {
      this.$store.commit(
        "categories/storeData",
        (await this.$axios.get("http://localhost:8080/categoryList")).data
      );
    },
  },
});
</script>
