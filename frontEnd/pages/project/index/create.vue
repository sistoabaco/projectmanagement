<template>
  <div class="flex flex-wrap w-full">
    <Modal
      :title="'Adicionar novo projecto'"
      size="xl"
      @close="$router.push({ name: 'project' })"
    >
      <div class="grid grid-cols-2 gap-3 space-y-px w-max">
        <TextInput
          v-model="project.projectName"
          label="Nome do projecto"
          placeholder="Nome do projecto"
          class="col-span-2"
          required
        />
        <TextInput
          v-model="project.localImplementation"
          label="Local de implementacao"
          placeholder="Local de implementacao"
          class=""
          required
        />
        <TextInput
          v-model="project.budget"
          type="number"
          label="Orcamento"
          placeholder="Orcamento"
          class=""
          required
        />
        <TextInput
          v-model="project.balanceAvailable"
          type="number"
          label="Saldo disponivel"
          placeholder="Saldo disponivel"
          class=""
          required
        />
        <TextInput
          v-model="project.typeCurrency"
          label="Tipo de moeda"
          placeholder="Tipo de moeda"
          class=""
          required
        />
        <TextInput
          v-model="project.dateStart"
          type="date"
          label="Data de inicio"
          placeholder="Data de inicio"
          class=""
          required
        />
        <TextInput
          v-model="project.dateEnd"
          type="date"
          label="Data final"
          placeholder="Data final"
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
            @close="$router.push({ name: 'project' })"
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
    project: {
      balanceAvailable: "",
      budget: "",
      projectName: "",
      dateEnd: "",
      dateStart: "",
      localImplementation: "",
      status: "",
      typeCurrency: "",
    },
  }),

  methods: {
    async handleSubmit() {
        await this.$axios.post("http://localhost:8080/saveProject", this.project, {
        }) .then(()=>{

        })

        this.$nuxt.refresh()
        this.$router.push({ name: 'project' })
    },
  },
};
</script>
