23.10.17(ProductView 작업 중)

ProductViewResponse에서 `log.info(productEntity.toString)` 을 호출 -> ProductEntity에는 
`
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prodCate1")
    private ProductCate1Entity prodCate1; 
`
ProductCate1Entity를 참조하는 prodCate1 존재

ProductCate1Entity 에는
`
    public void addProduct(ProductEntity productEntity) {
        this.products.add(productEntity);
        productEntity.setProdCate1(this);
    }
`
ProductEntity 를 참조
이런식으로 순환 참조를 하기때문에 stackoverflow 오류가 생김
이런 경우 해결방안으로 entity와 request, response의 중간에서 걸러주는 DTO를 사용해주면 된다.

관련 설명으로 https://data-make.tistory.com/727 참조


23.10.19
# jpa
- dirtychecking

23.10.25
# restcontroller
- serializing, deserializing