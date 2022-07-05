<template>
  <div>
    <page
      class="py-10 px-8"
      title="Projectos"
      sub-title="Lista de projectos existentes"
    >
      <div class="flex flex-col space-y-6">
        <div class="flex flex-row space-x-2 justify-end">
          <TextInput label="" placeholder="Pesquisar" class="w-1/4 px-2" />
          <!--<div>
            <AppButton
              class="flex text-black"
              label="Imprimir"
              variant="white"
              icon
              size="large"
            >
              <template #icon>
                <print />
              </template>
            </AppButton>
          </div>
          <div>
            <AppButton
              class="flex text-white"
              label="Importar"
              variant="blue-400"
              icon
              size="large"
            >
              <template #icon>
                <AddUserIcon />
              </template>
            </AppButton>
          </div>-->
          <div>
            <AppButton
              class="flex text-white"
              label="Adicionar"
              variant="green"
              icon
              size="large"
              @click.native="
                $router.push({
                  name: 'project-index-create',
                })
              "
            >
              <template #icon>
                <AddUserIcon />
              </template>
            </AppButton>
          </div>
        </div>
        <div class="flex w-full">
          <Table
            class="w-full"
            :headers="projectHeaders"
            :items="projects"
            counter
            actions
          >
            <template #name="{ value: project }">
              <span class="flex font-semibold">{{ project.name }}</span>
            </template>
            <template #actions="{ value: project }">
              <div class="flex flex-wrap items-center space-x-2">
                <div
                  class="
                    flex
                    w-8
                    h-8
                    justify-items-center
                    p-2
                    rounded-sm
                    bg-yellow-500
                    text-white
                    cursor-pointer
                  "
                  @click="goToEdit(project)"
                >
                  <edit-outline />
                </div>
                <div
                  class="
                    flex
                    w-8
                    h-8
                    justify-items-center
                    p-2
                    rounded-sm
                    bg-red-500
                    text-white
                    cursor-pointer
                  "
                  @click="deleteProject(project)"
                >
                  <delete-outline />
                </div>
              </div>
            </template>
          </Table>
        </div>
      </div>
      <nuxt-child />
    </page>
  </div>
</template>

<script lang="ts">

import { defineComponent } from "@nuxtjs/composition-api";
import Page from "~/components/common/misc/Page.vue";
import AppButton from "~/components/common/misc/AppButton.vue";
import TextInput from "~/components/common/inputs/TextInput.vue";
import AddUserIcon from "~/assets/icons/add-user.vue";
import Table from "~/components/common/misc/Table.vue";
import EditOutline from "~/assets/icons/edit-outline.vue";
import DeleteOutline from "~/assets/icons/delete_outline.vue";

export default defineComponent({
  name: "Index",
  components: {
    Page,
    AppButton,
    TextInput,
    AddUserIcon,
    Table,
    EditOutline,
    DeleteOutline,
  },
  data: () => ({
    data: [],
    selectedProjects: {},
    projectHeaders: [
      {
        key: "projectName",
        title: "Nome do Projecto",
        class: "whitespace-no-wrap",
      },
      {
        key: "localImplementation",
        title: "Local de Implementacao",
        class: "whitespace-no-wrap",
      },
      {
        key: "dateStart",
        title: "inicio do projecto",
        class: "whitespace-no-wrap",
      },
      {
        key: "dateEnd",
        title: "fim do projecto",
        class: "whitespace-no-wrap",
      },
      {
        key: "budget",
        title: "Orcamento",
        class: "whitespace-no-wrap",
      },
      {
        key: "status",
        title: "Estado",
        class: "whitespace-no-wrap",
      },
    ],
  }),

  created() {
    this.fetch()
  },

  computed: {
    projects() {
      // console.log("Testando ");
      return this.$store.state.projects.data;
    },
  },

  methods: {
    async fetch() {
      this.$store.commit(
        "projects/storeData",
        (await this.$axios.get("http://localhost:8080/projectList")).data
      );
    },

    goToEdit(project: any) {
      this.$router.push({
        name: "users-index-id-edit",
        params: { project: project},
      });
    },
    // deleteProjectr(project: any) {
    // },
    // handleSubmitDelete() {
    //   this.showDeleteModal = !this.showDeleteModal;
    // },
  },
});
</script>
