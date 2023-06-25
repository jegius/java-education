# Инструкция по работе с Visual Studio Code для Java

Visual Studio Code (VS Code) - это открытый источник, основанный на стандартах редактор, который предоставляет поддержку TypeScript, JavaScript и Node.js с богатыми возможностями и поддержкой Node.js debug. В следующих шагах представлены процесс установки и настройки VS Code для работы с Java.


## Оглавление

- [<== Назад](../README.md)

====================================

- [Установка VS Code](#Установка-VS-Code)
- [Установка расширения для Java](#Установка-расширения-для-Java)
- [Создание/Открытие проекта Java](#Создание/Открытие-проекта-Java)
- [Запуск приложения Java](#Запуск-приложения-Java)
- [Рекомендуемые настройки](#Рекомендуемые-настройки)
- [Полезные ссылки](#полезные-ссылки)


## Установка VS Code

1. Перейдите к зеркалу загрузки Visual Studio Code `https://code.visualstudio.com/download`.
2. Выберите подходящую для вашей операционной системы версию VS Code.
3. Запустите установщик и следуйте инструкциям.

## Установка расширения для Java

1. Откройте Visual Studio Code.
2. Нажмите на иконку Extensions в боковом меню (или используйте сочетание клавиш Ctrl+Shift+X).
3. В строке поиска введите `Java Extension Pack`.
4. Нажмите на кнопку `Install`. Затем необходимо перезагрузить Visual Studio Code.

## Создание/Открытие проекта Java

1. Чтобы создать новый Java проект, перейдите в боковое меню и выберите `View` -> `Command Palette`.
2. В Command Palette введите и выберите `Java: Create Java Project`.
3. Следуйте инструкциям для создания проекта.

Чтобы открыть существующий Java проект, выберите `File` -> `Open Folder` и выберите вашу директорию проекта.

## Запуск приложения Java

1. Откройте файл `.java`, в котором находится ваш метод `main()`.
2. Нажмите F5 или выберите пункт `Debug` -> `Start Debugging` в верхнем меню, чтобы начать отладку приложения.
3. По завершении выполнения можно просмотреть вывод в консоли `Debug Console`.

## Рекомендуемые настройки

VS Code поддерживает настройку на уровне пользователя и рабочей среды, лежащую в файлах `.vscode/settings.json` в вашей рабочей папке. Для Java проекта рекомендуется следующие настройки:

```json
{
    "java.home": "/path/to/your/jdk", 
    "java.configuration.updateBuildConfiguration": "automatic",
    "java.errors.incompleteClasspath.severity": "ignore" 
}
```

В любое время вы можете изменить настройки в соответствии с вашими предпочтениями.

## Полезные ссылки

- [Обучающие материалы по Java в VS Code](https://code.visualstudio.com/docs/languages/java)
- [Устранение неполадок с Java в VS Code](https://code.visualstudio.com/docs/java/java-tutorial#_troubleshooting)
- [Страница расширения Java в Visual Studio Code](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)