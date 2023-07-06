# quill-finagle-mysql

[zio-quill](https://github.com/zio/zio-quill) integration with [finagle-mysql](https://twitter.github.io/finagle/).

Now [zio-quill doesn't support quill-finagle-mysql](https://github.com/zio/zio-quill/pull/2756), so this repository provides this feature.

## Develop

```sh
> docker compose up -d
> ./setup/setup.sh
> sbt test
```

## License

[License](LICENSE)
