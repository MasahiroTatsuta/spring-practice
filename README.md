# Spring Practice Project

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-brightgreen?style=for-the-badge&logo=springboot)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6.x-green?style=for-the-badge&logo=springsecurity)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

## 概要
本プロジェクトは、**Spring Boot** を活用した Web アプリケーション開発の総合演習リポジトリです。
堅牢な**ユーザー管理システム**と、動的な**あいさつ表示・登録機能**を軸に、実務レベルのセキュアな実装を追求しています。

---

## 主な機能

### 1. ユーザー管理システム
* **認証・認可**: Spring Security によるセッションベースの高度なログイン管理。
* **フルCRUD**: ユーザー情報の登録、一覧表示、詳細編集、および論理削除/物理削除の実装。
* **バリデーション**: Bean Validation (Hibernate Validator) を用いた、サーバーサイドでの厳格な入力値チェック。

### 2. あいさつ表示・登録機能
* **動的メッセージ管理**: ユーザーごとに独自の「あいさつ文」をデータベースに保存・管理。
* **パーソナライズ表示**: ログイン中のユーザーに合わせたメッセージの動的レンダリング。

---

## セキュリティ実装のこだわり
セキュリティを単なる「機能」ではなく「設計の根幹」として捉え、以下の対策を徹底しています。

* **パスワードの安全性**: `BCryptPasswordEncoder` を採用し、強力なハッシュ化アルゴリズムで保存。
* **偽造リクエスト対策**: すべての POST/PUT/DELETE 操作における **CSRF (Cross-Site Request Forgery)** トークン検証の強制。
* **多層防御**:
    * **XSS対策**: Thymeleaf の自動エスケープによるスクリプト注入防止。
    * **SQLインジェクション対策**: Spring Data JPA によるプレパアドステートメントの徹底。
* **認可制御**: ロールベースアクセス制御 (RBAC) による、一般ユーザーと管理者間のアクセス権限の厳密な分離。

---

## スクリーンショット
| ログイン画面 | ユーザー管理画面 |
|:---:|:---:|
| <img src="docs/screenshots/login_view.png" width="350"> | <img src="docs/screenshots/user_management.png" width="350"> |
| `login_view.png` | `user_management.png` |

| あいさつ登録フォーム | メッセージ一覧 |
|:---:|:---:|
| <img src="docs/screenshots/greeting_form.png" width="350"> | <img src="docs/screenshots/greeting_list.png" width="350"> |
| `greeting_form.png` | `greeting_list.png` |

---

## 技術スタック
* **言語**: Java 17
* **フレームワーク**: Spring Boot 3.x / Spring Security 6.x
* **データベース**: H2 Database (開発) / MySQL (本番想定)
* **ORM**: Spring Data JPA (Hibernate)
* **テンプレートエンジン**: Thymeleaf
* **ビルドツール**: Maven
* **開発環境**: Eclipse (Windows 11)

---

## セットアップと実行

1.  **クローン**:
    ```bash
    git clone [https://github.com/MasahiroTatsuta/spring-practice.git](https://github.com/MasahiroTatsuta/spring-practice.git)
    ```
2.  **インポート**:
    Eclipse で `Existing Maven Projects` としてインポートしてください。
3.  **実行**:
    ```bash
    ./mvnw spring-boot:run
    ```

---

## プロジェクト構造
```text
src/main/java/com/example/practice
├── config        # セキュリティ設定 (SecurityConfig 等)
├── controller    # UI制御・ルーティング
├── service       # 業務ロジック・トランザクション
├── repository    # データアクセス層 (JPA)
├── entity        # データベースエンティティ
└── dto           # データ転送オブジェクト (Form, Response)