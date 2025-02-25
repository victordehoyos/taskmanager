import { NbMenuItem } from '@nebular/theme';

const role = localStorage.getItem('role');

export const MENU_ITEMS: NbMenuItem[] = [
  {
    title: 'Gestión de Usuarios',
    icon: 'people-outline',
    link: '/pages/users',
    hidden: role !== 'Líder Técnico',
  },
  {
    title: 'Gestión de Tareas',
    icon: 'archive-outline',
    link: '/pages/tasks',
  },
];
