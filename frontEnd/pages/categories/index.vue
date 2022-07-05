<template>
  <div>
    <Page class="py-10 px-8" title="Categorias" sub-title="Lista de Categorias">
      <div class="flex flex-col space-y-6">
        <div class="flex flex-row space-x-2 justify-end">
          <TextInput label="" placeholder="Pesquisar" class="w-1/4 px-2" />
          <div>
            <AppButton
              class="flex text-white"
              label="Adicionar"
              variant="green"
              icon
              size="large"
              @click.native="
                $router.push({
                  name: 'categories-index-create',
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
            :headers="categoriesHeaders"
            :items="categories"
            counter
            actions
          >
            <template #actions="{ value: category }">
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
                  @click="goToEdit(category)"
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
                  @click="deleteUser(category)"
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

<script>
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
    data:[],
    selectedCategory: {},
    categoriesHeaders: [
      {
        key: "description",
        title: "Descricao",
        class: "whitespace-no-wrap",
      },
      {
        key: "salary",
        title: "salario",
        class: "whitespace-no-wrap",
      },
    ],
  }),

  created() {
    this.fetch()
  },

  computed: {
    categories() {
      // console.log("Testando " + this.$store.state.employees.data);
      // this.$nuxt.$set(this.$store.state.employees.data)
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

    goToEdit(category) {
      this.$router.push({
        name: "categories-index-id-edit",
        params: { category: category },
      });
      console.log(category);
    },
    deleteUser(category) {
    },
  },
});
</script>
