# Spring Practice Project

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-brightgreen?style=for-the-badge&logo=springboot)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)

<p align="center">
  <img src="docs/screenshots/header.png" alt="Header Image" width="800">
</p>

## 概要
Spring Boot を用いた Web アプリケーションの学習用プロジェクトです。
基本的な CRUD 操作、バリデーション、例外処理、およびデータベース連携の実装パターンを網羅しています。

## 主な機能
- **ユーザー管理**: 登録、編集、一覧表示
- **バリデーション機能**: 入力フォームの整合性チェック
- **ロギング**: アプリケーションログの出力管理
- **エラーハンドリング**: カスタムエラーページの表示

## スクリーンショット
| メインダッシュボード | データ入力画面 |
|:---:|:---:|
| <img src="docs/screenshots/main_dashboard.png" width="300"> | <img src="docs/screenshots/input_form.png" width="300"> |
| `main_dashboard.png` | `input_form.png` |

| データ一覧表示 | エラー発生時 |
|:---:|:---:|
| <img src="docs/screenshots/data_list.png" width="300"> | <img src="docs/screenshots/error_page.png" width="300"> |
| `data_list.png` | `error_page.png` |

## 技術スタック
- **Backend**: Java 17 / Spring Boot 3.x
- **Build Tool**: Maven
- **Template Engine**: Thymeleaf
- **Database**: H2 Database (Dev) / MySQL (Prod)
- **IDE**: Eclipse (Windows)

## セットアップ

### 1. クローン
```bash
git clone [https://github.com/MasahiroTatsuta/spring-practice.git](https://github.com/MasahiroTatsuta/spring-practice.git)
