package ru.hse.software.restaurant.Client.view;

public class ViewerPanelAction {

    public static void viewPanelActionAll() {
        System.out.println("""
                Команды:
                1 - авторизоваться
                2 - зарегистрироваться
                """);
    }
    public static void viewPanelActionUser() {
        System.out.println("""
                Команды:
                1 - Начать делать заказ
                2 - Добавить блюдо в заказ
                3 - Удалить блюдо из заказа
                4 - Отправить заказ в обработку
                5 - Дать полную информацию по моему заказу
                6 - Дать иноформацию по всем моим заказам
                7 - Оплатить заказ
                8 - Оплатить все заказы(долг по счету)
                9 - Показать мой счет
                10 - Выйти из аккаунта
                """);
    }

    public static void viewPanelActionAdmin() {
        System.out.println("""
                Команды:
                1 - добавить блюдо
                2 - удалить блюдо
                3 - изменить блюдо
                4 - выйти из аккаунта
                """);
    }
}




