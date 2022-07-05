<template>
  <div class="flex flex-wrap w-full">
    <Modal
      :title="'Adicionar nova Categoria'"
      size="xl"
      @close="$router.push({ name: 'category' })"
    >
      <div class="grid grid-cols-2 gap-3 space-y-px w-max">
        <TextInput
          v-model="category.description"
          label="Nome da Categoria"
          placeholder="Nome da Categoria"
          class="col-span-2"
          required
        />
        <TextInput
          v-model="category.salary"
          type="number"
          label="Salario"
          placeholder="Salario"
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
            @close="$router.push({ name: 'category' })"
          />
        </div>
      </template>
    </Modal>
  </div>
</template>

<script lang="ts">
import Modal from "~/components/common/misc/Modal.vue";
import AppButton from "~/components/common/misc/AppButton.vue";
import TextInput from "~/components/common/inputs/TextInput.vue";
import SelectInput from "~/components/common/inputs/SelectInput.vue";

export default {
  components: { Modal, AppButton, TextInput, SelectInput },

  data: () => ({
  category: {
      description: "",
      salary: "",
    },
  }),

  methods: {
    async handleSubmit() {
        await this.$axios.post("http://localhost:8080/saveCategory", this.category, {
        }) .then(()=>{

        })
        this.$nuxt.refresh()
        this.$router.push({ name: 'category' })
    },
  },
};
</script>
