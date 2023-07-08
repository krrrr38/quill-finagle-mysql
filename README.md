# quill-finagle-mysql

![CI](https://github.com/krrrr38/quill-finagle-mysql/workflows/CI/badge.svg)
[![Maven Central](https://img.shields.io/maven-central/v/com.krrrr38/quill-finagle-mysql_2.13.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:com.krrrr38%20AND%20a:quill-finagle-mysql_2.13)

[zio-quill](https://github.com/zio/zio-quill) integration with [finagle-mysql](https://twitter.github.io/finagle/).

Now [zio-quill doesn't support quill-finagle-mysql](https://github.com/zio/zio-quill/pull/2756), so this repository provides this feature.

## Develop

```sh
> docker compose up -d
> ./setup/setup.sh
> sbt test
```

## Resolve next version

When adding `minor` and `major` github pull request label, next version would be resolved by them.

## Publish

- SNAPSHOT Release
    - Each snapshot release is published by main branch ci.
- Release
    - Merge [tagpr](https://github.com/Songmu/tagpr) Pull Request, then publish release on git tag ci.

## License

[License](LICENSE.txt)
